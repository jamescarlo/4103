# griffon-start

### Requirements
* Java SE Development Kit
* Gradle
* Any text editor (Sublime Text, VS Code, Vim, Atom)
* Scene Builder

### Installation
1. Java SE Development kit. See here [How to Download & Install Java JDK 8 in Windows](https://www.guru99.com/install-java.html)
2. Gradle. See here [How to install Gradle on Windows](https://www.bryanlor.com/blog/gradle-tutorial-how-install-gradle-windows)
3. Scene Builder. See here [Gluon Scene Builder](http://gluonhq.com/products/scene-builder/)

### Quick Start
```
git clone https://github.com/ivan-ortiz/griffon-start.git
cd griffon-start
gradlew run
```

### Database Operations
1. Fetch data
   ```
   /** 
    * Create a query object
    */
   MultiMap query = dbquery.map();
   /**
    * Assign table, conditions
    */
   query.put("table",         "cars");
   query.put("condition",     "color = red");
   query.put("condition:and", "price >= 900000");
   /**
    * Store results in a map
    */
   Map<String, Map> results = dbquery.get(query);
   
   /**
    * Display total results
    */
   util.toast( results.size() );
   /**
    * Display the first data
    */
   util.toast( results.get(0).get("color") );
   ```
2. Insert data
