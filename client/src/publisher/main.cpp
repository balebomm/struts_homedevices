#include <stdio.h>

#include "publisher/publisher.h"

int main() {
  struct mosquitto * mosq;
  int rc;

  mosquitto_lib_init();

  mosq = mosquitto_new(NULL, true, NULL);
  if (mosq == NULL) {
    fprintf(stderr, "Err: Out of memory.\n");
    return 1;
  }

  mosquitto_connect_callback_set(mosq, on_publish_connect);
  mosquitto_publish_callback_set(mosq, on_publish);

  rc = mosquitto_connect(mosq, "localhost", 1883, 60);
  if (rc != MOSQ_ERR_SUCCESS) {
    mosquitto_destroy(mosq);
    fprintf(stderr, "Err: %s\n", mosquitto_strerror(rc));
    return 1;
  }

  rc = mosquitto_loop_start(mosq);
  if (rc != MOSQ_ERR_SUCCESS) {
    mosquitto_destroy(mosq);
    fprintf(stderr, "Err: %s\n", mosquitto_strerror(rc));
    return 1;
  }

  while (1) {
    publish_sesor_data(mosq);
  }

  mosquitto_lib_cleanup();
  return 0;
}
