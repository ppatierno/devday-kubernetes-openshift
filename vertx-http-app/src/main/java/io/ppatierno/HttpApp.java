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

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class HttpApp {

    private static String myFile = null;
    private static String myEnv = null;

    public static void main(String[] args) {

        try {
            BufferedReader b = new BufferedReader(new FileReader("/tmp/my-file.txt"));
            myFile = b.readLine();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        myEnv = System.getenv("MY_ENV");

        Vertx vertx = Vertx.vertx();

        vertx.createHttpServer()
                .requestHandler(request -> {

                    HttpServerResponse response = request.response();

                    String body = "Hello Vert.x HTTP from " + System.getenv("HOSTNAME");
                    if (myEnv != null) {
                        body = body + "\n my-env = " + myEnv;
                    }
                    if (myFile != null) {
                        body = body + "\n my-file = " + myFile;
                    }
                    response.headers().add("Content-Length", String.valueOf(body.length()));
                    response.write(body).end();

                }).listen(8080);
    }
}
