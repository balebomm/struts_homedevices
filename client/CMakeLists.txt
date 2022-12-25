cmake_minimum_required(VERSION 3.8)

project("client")
if (COMMAND cmake_policy)
  cmake_policy(SET CMP0003 NEW)
  cmake_policy(SET CMP0008 NEW)
  cmake_policy(SET CMP0023 NEW)
endif()

set(CMAKE_CXX_STANDARD 20)
set(CMAKE_CXX_STANDARD_REQUIRED ON)
set(CMAKE_CXX_EXTENSIONS OFF)

set(CMAKE_MODULE_PATH ${CMAKE_MODULE_PATH} ${CMAKE_CURRENT_SOURCE_DIR}/support/cmake)

if (NOT EMSCRIPTEN)
  include(TestBigEndian)
  test_big_endian(WORDS_BUGENDIAN)
endif()

if (NOT CMAKE_BUILD_TYPE)
  set(CMAKE_BUILD_TYPE "Debug" CACHE STRING "Default build type is Debug.")
endif()

set(CLIENT_DEBUG_MODE OFF)
if (CMAKE_BUILD_TYPE STREQUAL "Debug")
  set(CLIENT_DEBUG_MODE ON)
endif()

option(CLIENT_PEDANTIC "Enable extra warning using standard language flags" ON)
option(CLIENT_WERROR   "Halt compiler warnings into errors" ON)
option(CLIENT_TEST     "Generate test target module when debug" ${CLIENT_DEBUG_MODE})
option(CLIENT_DOC      "Generate documentation module" OFF)

include(CheckCXXCompilerFlag)

if (CMAKE_CXX_COMPILER_ID MATCHES "GNU")
  set(CLIENT_PEDANTIC_FLAGS -pedantic -Wall -pedantic-errors -Werror
    -Wredundant-decls -Wwrite-strings -Wpointer-arith
    -Wformat=2 -Wmissing-include-dirs -Wcast-align
    -Wctor-dtor-privacy -Wdisabled-optimization
    -Winvalid-pch -Wundef
    -Wno-ctor-dtor-privacy -Wno-format-nonliteral)
  if (NOT CMAKE_CXX_COMPILER_VERSION VERSION_LESS 4.6)
    set(CLIENT_PEDANTIC_FLAGS ${CLIENT_PEDANTIC_FLAGS}
      -Wno-dangling-else -Wno-unused-local-typedefs)
  endif()
  if (NOT CMAKE_CXX_COMPILER_VERSION VERSION_LESS 5.0)
    set(CLIENT_PEDANTIC_FLAGS ${CLIENT_PEDANTIC_FLAGS}
      -Wtrampolines -Wzero-as-null-pointer-constant -Wuseless-cast
      -Wvector-operation-performance -Wsized-deallocation)
  endif()
  if (NOT CMAKE_CXX_COMPILER_VERSION VERSION_LESS 6.0)
    set(CLIENT_PEDANTIC_FLAGS ${CLIENT_PEDANTIC_FLAGS} -Wshift-overflow=2
      -Wnull-dereference -Wduplicated-cond)
    check_cxx_compiler_flag(-Wzero-as-null-pointer-constant HAS_NULLPTR_WARNING)
    if (HAS_NULLPTR_WARNING)
      set(CLIENT_PEDANTIC_FLAGS ${CLIENT_PEDANTIC_FLAGS}
        -Wzero-as-null-pointer-constant)
    endif()
  endif()
  set(CLIENT_WERROR_FLAGS -Werror)
endif()

if (CMAKE_CXX_COMPILER_ID MATCHES "Clang")
  set(CLIENT_PEDANTIC_FLAGS -Wall -Wextra -pedantic -Wundef
    -Wdeprecated -Wweak-vtables -Wshadow
    -Wno-gnu-zero-variadic-macro-arguments)
  set(CLIENT_WERROR_FLAGS -Werror)
endif()

if (MSVC)
  set(CLIENT_PEDANTIC_FLAGS /W3)
  set(CLIENT_WERROR_FLAGS /WX)
endif()

file(GLOB_RECURSE COMMON_CXX_SOURCES "src/common/*.cpp")
add_library(common ${COMMON_CXX_SOURCES})

target_include_directories(common PUBLIC ${CMAKE_CURRENT_SOURCE_DIR}/src)

if (CLIENT_PEDANTIC)
  target_compile_options(common PUBLIC ${CLIENT_PEDANTIC_FLAGS})
endif()
if (CLIENT_WERROR)
  target_compile_options(common PUBLIC ${CLIENT_WERROR_FLAGS})
endif()
if (CLIENT_DEBUG_MODE)
  target_compile_definitions(common PUBLIC CLIENT_DEBUG)
endif()

find_package(OpenSSL REQUIRED)
find_package(mosquitto REQUIRED)

target_include_directories(common PUBLIC ${MOSQUITTO_INCLUDE_DIRS})
target_link_libraries(common PUBLIC ${MOSQUITTO_LIBRARIES})
target_link_libraries(common PUBLIC OpenSSL::SSL OpenSSL::Crypto)

file(GLOB_RECURSE PUBLISHER_CXX_SOURCES "src/publisher/*.cpp")
file(GLOB_RECURSE SUBSCRIBER_CXX_SOURCES "src/subscriber/*.cpp")

add_executable(publisher ${PUBLISHER_CXX_SOURCES})
add_executable(subscriber ${SUBSCRIBER_CXX_SOURCES})

target_link_libraries(publisher PUBLIC common)
target_link_libraries(subscriber PUBLIC common)

find_package(libmysql REQUIRED)
target_link_libraries(subscriber PRIVATE ${MYSQL_LIBRARIES})

configure_file(define.h.cmake define.h)
