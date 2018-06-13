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
   util.toast(results.size());
   /**
    * Display the first data
    */
   util.toast(results.get(0).get("color"));
   ```
2. Insert data
   ```
   /** 
    * Create a query object
    */
   MultiMap query = dbquery.map();
   /**
    * Assign table, values
    */
   query.put("table", "cars");
   query.put("set",   "color = blue");
   query.put("set",   "price = 800000");
   /**
    * Execute query
    */
   dbquery.save(query);
   ```
3. Update data
   ```
   /** 
    * Create a query object
    */
   MultiMap query = dbquery.map();
   /**
    * Assign table, values, conditions
    */
   query.put("table",        "cars");
   query.put("set",          "color = green");
   query.put("condition",    "color != red");
   quert.put("condition:or", "color != blue");
   /**
    * Execute query
    */
   dbquery.update(query);
   ```
4. Delete data
   ```
   /** 
    * Create a query object
    */
   MultiMap query = dbquery.map();
   /**
    * Assign table, conditions
    */
   query.put("table",        "cars");
   query.put("condition",    "color = red");
   quert.put("condition:or", "color = blue");
   /**
    * Execute query
    */
   dbquery.delete(query);
   ```
