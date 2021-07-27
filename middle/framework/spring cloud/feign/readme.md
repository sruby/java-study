# skywalking agent
* vm 
  -javaagent:/Users/macuser/soft/apache-skywalking-apm-bin-es7/agent/skywalking-agent.jar=agent.instance_name=DemoConsumerApplication-local,agent.namespace=sruby-namespace
* env
  SW_AGENT_NAME=DemoConsumerApplication;SW_AGENT_COLLECTOR_BACKEND_SERVICES=127.0.0.1:11800;SW_AGENT_SPAN_LIMIT=2000;SW_NAMESPACE=sruby-demo;SW_SEARCHABLE_TAG_KEYS=http.method,status_code,db.type,db.instance,mq.queue,mq.topic,mq.broker,tag.demo,stock_code