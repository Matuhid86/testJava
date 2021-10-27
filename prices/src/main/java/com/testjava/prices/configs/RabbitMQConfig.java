package com.testjava.prices.configs;

/*
 * @Configuration public class RabbitMQConfig {
 * 
 * @Value("${priceQueue}") private String priceQueue;
 * 
 * @Value("${allQueue}") private String allQueue;
 * 
 * @Value("${bindPriceQueue}") private String bindQueuePrice;
 * 
 * @Value("${bindAllQueue}") private String bindAllQueue;
 * 
 * @Value("${topicExchangeName}") private String topicExchangeName;
 * 
 * @Bean public Queue priceQueue() { return new Queue(this.priceQueue, false); }
 * 
 * @Bean public Queue allQueue() { return new Queue(this.allQueue, false); }
 * 
 * @Bean public TopicExchange topicExchange() { return new
 * TopicExchange(this.topicExchangeName); }
 * 
 * @Bean public Binding priceBinding(Queue priceQueue, TopicExchange
 * topicExchange) { return
 * BindingBuilder.bind(priceQueue).to(topicExchange).with(this.bindQueuePrice);
 * }
 * 
 * @Bean public Binding allBinding(Queue allQueue, TopicExchange topicExchange)
 * { return
 * BindingBuilder.bind(allQueue).to(topicExchange).with(this.bindAllQueue); }
 * 
 * @Bean public SimpleMessageListenerContainer
 * containerPriceRabbitMQ(ConnectionFactory connectionFactory,
 * MessageListenerAdapter consumerPrice) { SimpleMessageListenerContainer
 * container = new SimpleMessageListenerContainer();
 * container.setConnectionFactory(connectionFactory);
 * container.setQueueNames(this.priceQueue);
 * container.setMessageListener(consumerPrice); return container; }
 * 
 * @Bean public MessageListenerAdapter consumerPrice(PriceConsumer
 * priceConsumer) { return new MessageListenerAdapter(priceConsumer,
 * "receivePrice"); }
 * 
 * }
 */
