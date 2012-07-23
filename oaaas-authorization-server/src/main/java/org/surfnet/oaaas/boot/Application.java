/*
 * Copyright 2012 SURFnet bv, The Netherlands
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.surfnet.oaaas.boot;

import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Environment;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.surfnet.oaaas.resource.ResourceServerResource;

public class Application extends Service<ApplicationConfiguration> {

  public static void main(String[] args) throws Exception {
    new Application("OAaaS").run(args);
  }

  public Application(String name) {
    super(name);
  }

  @Override
  protected void initialize(ApplicationConfiguration configuration, Environment environment) throws Exception {

    AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
    ctx.register(SpringConfiguration.class);
    ctx.refresh();

    environment.addResource(ctx.getBean(ResourceServerResource.class));
  }
}
