#include "subscriber/subscriber.h"

#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <unistd.h>
#include <string>
#include <time.h>

#include "mosquitto.h"
#include "mysql/mysql.h"

#include "common/univalue.h"

void on_subscribe_connect(struct mosquitto *mosq, void *obj, int reason_code) {
  int rc;

  printf("on_subscribe_connect: %s\n", mosquitto_connack_string(reason_code));
  if (reason_code != 0) {
    mosquitto_disconnect(mosq);
  }

  rc = mosquitto_subscribe(mosq, NULL, "example/temperature", 2);
  if (rc != MOSQ_ERR_SUCCESS) {
    fprintf(stderr, "Err subscribing: %s\n", mosquitto_strerror(rc));
    mosquitto_disconnect(mosq);
  }
}

void on_subscribe(struct mosquitto *mosq, void *obj, int mid, int qos_count, const int *granted_qos) {
  int i;
  bool have_subscription = false;

  for (i = 0; i < qos_count; ++i) {
    printf("on_subscribe: %d: granted qos = %d\n", i, granted_qos[i]);
    if (granted_qos[i] <= 2) {
      have_subscription = true;
    }
  }

  if (have_subscription == false) {
    fprintf(stderr, "Error: All subscriptions rejected.\n");
    mosquitto_disconnect(mosq);
  }
}

void on_message(struct mosquitto * mosq, void *obj, const struct mosquitto_message *msg) {
  std::string msg_json{(char *)msg->payload};

  common::UniValue uv;
  uv.read(msg_json);

  MYSQL_BIND bind[2];
  memset(bind, 0, sizeof(bind));

  int idthietbi = uv["idthietbi"].getInt<int>();
  bind[0].buffer_type = MYSQL_TYPE_LONG;
  bind[0].buffer = (void*)&idthietbi;

  float giatri = uv["giatri"].get_real();
  bind[1].buffer_type = MYSQL_TYPE_FLOAT;
  bind[1].buffer = (void*)&giatri;

  mysql_stmt_bind_param(stmt, bind);
  if (mysql_stmt_execute(stmt)) {
    fprintf(stderr, "mysql_stmt_execute(), failed: %s\n", mysql_stmt_error(stmt));
  }
}
