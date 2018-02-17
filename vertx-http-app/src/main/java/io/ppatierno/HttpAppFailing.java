/**
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.ù
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package io.ppatierno;

import io.netty.handler.codec.http.HttpResponseStatus;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpAppFailing {

    private static Logger log = LoggerFactory.getLogger(HttpAppFailing.class);

    private static int count = 0;

    public static void main(String[] args) {

        Vertx vertx = Vertx.vertx();

        vertx.createHttpServer()
                .requestHandler(request -> {

                    count++;
                    log.info("Hitting time = {}", count);

                    HttpServerResponse response = request.response();

                    if (count <= 3) {
                        String body = "Hello Vert.x HTTP from " + System.getenv("HOSTNAME");
                        response.headers().add("Content-Length", String.valueOf(body.length()));
                        response.write(body).end();
                    } else {
                        log.info("Failure !!");
                        response.setStatusCode(HttpResponseStatus.INTERNAL_SERVER_ERROR.code());
                        response.end();
                    }

                }).listen(8080, ar -> {
                    if (ar.succeeded()) {
                        log.info("HTTP server started !");
                    } else {
                        log.error("Error on starting the HTTP server !");
                    }
                });
    }
}
