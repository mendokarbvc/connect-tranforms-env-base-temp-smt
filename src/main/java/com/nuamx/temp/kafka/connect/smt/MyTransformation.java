/*
 * Copyright © 2019 Christopher Matta (chris.matta@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

 package com.nuamx.temp.kafka.connect.smt;

 import org.apache.kafka.common.config.ConfigDef;
 import org.apache.kafka.connect.connector.ConnectRecord;
 import org.apache.kafka.connect.transforms.Transformation;
 import org.apache.kafka.connect.transforms.util.SimpleConfig;
 
 import java.util.Map;
 
 public abstract class MyTransformation<R extends ConnectRecord<R>> implements Transformation<R> {
 
   public static final String OVERVIEW_DOC =
     "Overview of what this transformation does.";
 
   private interface ConfigName {
     String MY_CONFIG_PARAM = "my.config.param";
   }
 
   public static final ConfigDef CONFIG_DEF = new ConfigDef()
     .define(ConfigName.MY_CONFIG_PARAM, ConfigDef.Type.STRING, "default_value", ConfigDef.Importance.HIGH,
       "Description of my configuration parameter.");
 
   private String myConfigParam;
 
   @Override
   public void configure(Map<String, ?> props) {
     final SimpleConfig config = new SimpleConfig(CONFIG_DEF, props);
     myConfigParam = config.getString(ConfigName.MY_CONFIG_PARAM);
 
     // Additional configuration if needed
     // Example: Initialize caches, resources, etc.
   }
 
   @Override
   public R apply(R record) {
     // Implementation of transformation logic
     return applyTransform(record);
   }
 
   @Override
   public ConfigDef config() {
     return CONFIG_DEF;
   }
 
   @Override
   public void close() {
     // Clean up resources if any
   }
 
   protected abstract R applyTransform(R record);
 
 }
 