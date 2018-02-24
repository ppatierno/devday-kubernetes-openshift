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

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FortuneWebServer {

    private static Logger log = LoggerFactory.getLogger(FortuneWebServer.class);

    public static void main(String[] args) {

        Vertx vertx = Vertx.vertx();

        vertx.createHttpServer()
                .requestHandler(request -> {

                    HttpServerResponse response = request.response();

                    BufferedReader b = null;
                    String body = null;
                    try {
                        b = new BufferedReader(new FileReader("/usr/share/fortunews/html/index.html"));
                        body = b.readLine();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        if (b != null) {
                            try {
                                b.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    log.info("Replying : {}", body);

                    response.headers().add("Content-Length", String.valueOf(body.length()));
                    response.write(body).end();

                }).listen(8080, ar -> {
            if (ar.succeeded()) {
                log.info("HTTP server started !");
            } else {
                log.error("Error on starting the HTTP server !");
            }
        });
    }
}
