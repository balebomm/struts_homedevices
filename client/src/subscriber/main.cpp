#include <stdio.h>
#include <signal.h>
#include <string.h>

#include "mysql/mysql.h"

#include "subscriber/subscriber.h"

#define db_host "127.0.0.1"
#define db_username "root"
#define db_password "password"
#define db_database "homedevices"
#define db_port 3306

#define db_query "INSERT INTO dulieuthietbi(idthietbi,giatri) VALUES(?,?)"

MYSQL_STMT *stmt = NULL;

int main() {
  MYSQL *connection;
  char clientid[24];
  struct mosquitto *mosq;
  int rc;

  mosquitto_lib_init();

  connection = mysql_init(nullptr);
  if (connection == nullptr) {
    fprintf(stderr, "Err: mysq conn is null");
    return 1;
  }

  connection = mysql_real_connect(connection, db_host, db_username, db_password, db_database, db_port, NULL, 0);
  if (connection == nullptr) {
    fprintf(stderr, "Err: real mysq conn is null");
    return 1;
  }

  mysql_library_init(0, NULL, NULL);
  stmt = mysql_stmt_init(connection);

  mysql_stmt_prepare(stmt, db_query, strlen(db_query));

  memset(clientid, 0, 24);
  snprintf(clientid, 23, "mysql_log_%d", getpid());

  mosq = mosquitto_new(clientid, true, connection);
  if (mosq == NULL) {
    fprintf(stderr, "Err: Out of memory");
    return 1;
  }

  mosquitto_connect_callback_set(mosq, on_subscribe_connect);
  mosquitto_subscribe_callback_set(mosq, on_subscribe);
  mosquitto_message_callback_set(mosq, on_message);

  rc = mosquitto_connect(mosq, "localhost", 1883, 60);
  if (rc != MOSQ_ERR_SUCCESS) {
    mosquitto_destroy(mosq);
    fprintf(stderr, "Err: %s\n", mosquitto_strerror(rc));
    return 1;
  }

  printf("Connect success, ready for loop forever...\n");
  mosquitto_loop_forever(mosq, -1, 1);
  mosquitto_lib_cleanup();
  mysql_stmt_close(stmt);
  mysql_close(connection);
  mysql_library_end();

  return 0;
}
