#ifndef CLIENT_CORE_H
#define CLIENT_CORE_H

#include "mosquitto.h"

void on_publish_connect(struct mosquitto *mosq, void *obj, int reason_code);
void on_publish(struct mosquitto *mosq, void *obj, int mid);
void on_message(struct mosquitto *mosq, void *obj, const struct mosquitto_message *msg);
int get_temperature(void);
void publish_sesor_data(struct mosquitto *mosq);

#endif // CLIENT_CORE_H
