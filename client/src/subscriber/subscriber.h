#ifndef CLIENT_CORE_H
#define CLIENT_CORE_H

#include "mosquitto.h"
#include "mysql/mysql.h"

extern MYSQL_STMT *stmt;

void on_subscribe_connect(struct mosquitto *mosq, void *obj, int reason_code);
void on_subscribe(struct mosquitto *mosq, void *obj, int mid, int qos_count, const int *granted_qos);
void on_message(struct mosquitto *mosq, void *obj, const struct mosquitto_message *msg);
int get_temperature(void);
void publish_sesor_data(struct mosquitto *mosq);

#endif // CLIENT_CORE_H
