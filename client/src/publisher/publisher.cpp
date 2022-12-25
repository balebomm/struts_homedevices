#include "publisher/publisher.h"

#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <unistd.h>
#include <string>

#include "mosquitto.h"

#include "common/univalue.h"

void on_publish_connect(struct mosquitto *mosq, void *obj, int reason_code) {
  printf("on_publish_connect: %s\n", mosquitto_connack_string(reason_code));
  if (reason_code != 0) {
    mosquitto_disconnect(mosq);
  }
}

void on_publish(struct mosquitto *mosq, void *obj, int mid) {
  printf("Message with mid %d has been published.\n", mid);
}

void on_message(struct mosquitto * mosq, void *obj, const struct mosquitto_message *msg) {
  printf("%s %d %s\n", msg->topic, msg->qos, (char *)msg->payload);
}

int get_temperature(void) {
  sleep(1);
  return random() % 100;
}

void publish_sesor_data(struct mosquitto *mosq) {
  int rc;

  common::UniValue uv(common::UniValue::VOBJ);
  uv.pushKV("idthietbi", 1);
  uv.pushKV("giatri", get_temperature());

  std::string msg = uv.write();

  rc = mosquitto_publish(mosq, NULL, "example/temperature", msg.size(), msg.c_str(), 2, false);
  if (rc != MOSQ_ERR_SUCCESS) {
    fprintf(stderr, "Error publishing: %s\n", mosquitto_strerror(rc));
  }
}
