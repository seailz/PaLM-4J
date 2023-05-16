# PaLM-4J
✨ Java library for [Google's PaLM API](https://developers.generativeai.google/). This simplifies the use of the REST API!

## Documentation
Here's a quick guide on how to get started:

First, you'll want to create a new instance of the `PaLM` class. Here's an example:
```java
PaLM palm = PaLM.newPaLM("YOUR_API_KEY_HERE");
```
Your API key can be obtained from Google's [MakerSuite](https://makersuite.google.com//app/apikey).

### Retrieving Models
Next, you can either retrieve a list of all avaliable models like this:
```java
List<Model> models = palm.getModels().awaitCompleted();
```
Or, you can retrieve a specifixc model by name like this:
```java
Model model = palm.getModel(/* Name: */ "models/chat-bison-001").awaitCompleted();
```
### Generating Text
Once you've selected a model, you can start generating some text.

#### Chat messages
One way of generating text is using the Message system. It replicates a chat system and allows the model to respond as if it was a chatbot.
Here's an example of how to do that:

```java
 GenerateMessageResponse res = GenerateMessageRequest.newRequest()
                .setModel(model)
                .setPaLM(palm)
                setPrompt(
                        MessagePrompt.newPrompt()
                                .addMessage( // You can add as many messages as you like
                                        Message.newMessage()
                                                .setAuthor("Seailz")
                                                .setContent("Hello!")
                                )
                                .setContext("You're an alien from outer space, and you're currently in hyperspace.") // Gives the model context for answering the prompt.
                ).setTemperature(0.5) // Optional: The temprature is a double between 0 and 1 that represents how random the responses will be.
                .setCandidateCount(2) // Optional: You can specify the amount of options you want the model to provide.
                .execute().awaitCompleted();
```
You can then get the model's response like this:
```java
String response = res.getOptions().get(0).getContent();
```
The options array is a list of `Message`s that the model responded with.

#### Normal Text Generation
You can also generate text normally - the model will try to guess what comes next in the text.
```java
GenerateTextResponse res = GenerateTextRequest.newRequest()
                .setModel(model)
                .setPaLM(palm)
                .setPrompt("My favourite food is ")
                .setTemperature(0.5) // Optional: The temprature is a double between 0 and 1 that represents how random the responses will be.
                .setCandidateCount(2) //  Optional: You can specify the amount of options you want the model to provide.
                .execute().awaitCompleted()
```

You can then get the model's response like this:
```java
String response = res.getOptions().get(0).getOutput();
```

An example response using this method is:
```
My favourite food is pizza. I love the combination of crispy crust, melted cheese, and flavorful sauce. I also enjoy the variety of toppings that can be added to pizza, such as pepperoni, sausage, mushrooms, onions, peppers, and olives. Pizza is a delicious and versatile food that can be enjoyed by people of all ages.
```
