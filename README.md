# KrakenManager
**v1.0.9**

This is a Java Based library useful to work with Kraken's API service.

## Implementation

Add the JitPack repository to your build file

### Gradle

- Add it in your root build.gradle at the end of repositories

    #### Gradle (Short)
         
    ```gradle
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
    ```

    #### Gradle (Kotlin)
         
    ```gradle
    repositories {
        ...
        maven("https://jitpack.io")
    }
    ```
    
- Add the dependency

    #### Gradle (Short)
         
    ```gradle
    dependencies {
        implementation 'com.github.N7ghtm4r3:KrakenManager:1.0.9'
    }
    ```

    #### Gradle (Kotlin)
         
    ```gradle
    dependencies {
        implementation("com.github.N7ghtm4r3:KrakenManager:1.0.9")
    }
    ```

### Maven

- Add it in your root build.gradle at the end of repositories

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```
- Add the dependency

```xml
<dependency>
    <groupId>com.github.N7ghtm4r3</groupId>
    <artifactId>KrakenManager</artifactId>
    <version>1.0.9</version>
</dependency>
```

## 🛠 Skills

- Java

## Usage/Examples

#### Execution

```java

// init a Kraken manager
try {
    KrakenMarketManager manager = new KrakenMarketManager();
} catch(Exception e) {
    e.printStackTrace();
}
```

To avoid re-entering credentials for each manager, you can instantiate managers like this with the **ARCS**:

```java
// choose the manager for example: KrakenUserDataManager, KrakenUserTradingManager, etc 
KrakenPrivateManager firstManager = new KrakenPrivateManager(/* params of the constructor chosen */,"apiKey","apiSign");
// and then use it 
firstManager.makeSomething();
// you don't need to insert all credentials to make manager work
KrakenPrivateManager secondManager = new KrakenPrivateManager(); // same credentials used
// and then use it
secondManager.makeSomething();
```

#### Responses

Library give to you the opportunity to customize the return object after a request, the possibilities are:

- **JSON:** return response formatted as **JSON** (**org.json.JSONObject** or **org.json.JSONArray**)
- **STRING:** return response formatted as **String**
- **LIBRARY_OBJECT:** return response formatted as custom object offered by the library

```java
// choose the manager for example: Gmail, etc 
KrakenManager manager=new KrakenManager(/* params of the constructor chosen */);
// method to return directly a library given by library
manager.someRequest(); // in this case will be returned directly a LIBRARY_OBJECT
// method to customize the format of the return 
manager.someRequest(ReturnFormat.JSON); // in this case will be returned response in JSON format
```

### Errors handling

```java
try {
    System.out.println(manager.getAssetObject("BTCEUR"));
} catch (Exception e) {
    System.out.println(manager.getErrorResponse());
    //or
    manager.printErrorResponse();     
}
/* NOTE: if is not a request error will appear: "Error is not in api request, check out your code"
  and you will have to work on your code to manage error, you can also change default error message*/
```

## Authors

- [@N7ghtm4r3](https://www.github.com/N7ghtm4r3)

## Support

If you need help using the library or encounter any problems or bugs, please contact us via the following links:

- Support via <a href="mailto:infotecknobitcompany@gmail.com">email</a>
- Support via <a href="https://github.com/N7ghtm4r3/KrakenManager/issues/new">GitHub</a>

Thank you for your help!

## Badges

[![](https://img.shields.io/badge/Google_Play-414141?style=for-the-badge&logo=google-play&logoColor=white)](https://play.google.com/store/apps/developer?id=Tecknobit)
[![Twitter](https://img.shields.io/badge/Twitter-1DA1F2?style=for-the-badge&logo=twitter&logoColor=white)](https://twitter.com/tecknobit)

[![](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)](https://www.oracle.com/java/)

[![](https://jitpack.io/v/N7ghtm4r3/KrakenManager.svg)](https://jitpack.io/#N7ghtm4r3/KrakenManager)

## Donations

If you want support project and developer

| Crypto  | Address| Network |
| ------------- | ------------- | ------------- |
| ![](https://img.shields.io/badge/Bitcoin-000000?style=for-the-badge&logo=bitcoin&logoColor=white) | **3H3jyCzcRmnxroHthuXh22GXXSmizin2yp** | Bitcoin |
| ![](https://img.shields.io/badge/Ethereum-3C3C3D?style=for-the-badge&logo=Ethereum&logoColor=white)  | **0x1b45bc41efeb3ed655b078f95086f25fc83345c4**  | Ethereum |

If you want support project and developer
with <a href="https://www.paypal.com/donate/?hosted_button_id=5QMN5UQH7LDT4">PayPal</a>

Copyright © 2024 Tecknobit
