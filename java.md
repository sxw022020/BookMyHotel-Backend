- https://www.1point3acres.com/bbs/thread-981258-1-1.html
- https://www.digitalocean.com/community/tutorials/core-java-interview-questions-and-answers
- 作为补充:
  - https://www.1point3acres.com/bbs/thread-973970-1-1.html
### Java
---
1. **What is Object-Oriented Programming (OOP)?**
   - A fundamental programming paradigm that relies on the concept of classes and objects
   - It is used to structure a software program into simple, reusable pieces of code blueprints (usually called classes), which are used to create individual instances of objects
---
2. **What are 4 OOP key concepts, explain each one?**
   - (1) **Abstraction**
     - A process of hiding implementation details and exposing only the functionality to the user
     - This means the user will only know “what it does” rather than “how it does”
       - Example:
         - A driver will focus on the car functionality (Start/Stop -> Accelerate/Brake), the driver does not need to know how the Accelerate/Brake mechanism works internally.
     - 2 ways to achieve abstraction:
       - <1> Abstract class (0 to 100%)
         - The class should be abstract if a class has one or more abstract methods
         - An abstract class can have constructors, concrete methods, static methods, and a final method
         - The child class should override all the abstract methods of parents; otherwise, the child class should be declared with an abstract keyword
       - <2> Interface (100%)
   - (2) **Encapsulation**
     - The process of wrapping code and data together into a single unit.
     - It is the mechanism that binds together code and the data it manipulates.
     - Another way to think about encapsulation is, that it is a protective shield that prevents the data from being accessed by the code outside this shield.
     - Technically in encapsulation, the variables or data of a class are hidden from any other class and can be accessed only through any member function of its own class in which they are declared. As in encapsulation, the data in a class is hidden from other classes, so it is also known as data-hiding. Encapsulation can be achieved by Declaring all the variables in the class as private and writing public methods in the class to set and get the values of variables
     - In order to achieve encapsulation in Java follow certain steps as proposed below:
       - Declare the variables as private
       - Declare the setters and getters to set and get the variable values
       - Advantages:
         - <1> Control over data:
           - We can write the logic in the setter method to not store the negative values for an Integer. So in this way, we can control the data.
         - <2> Data Hiding:
           - The data members are private so other classes can’t access the data members.
   - (3) **Inheritance**
     - `extends`
     - A process of creating a new class from an existing class
     - In Java, inheritance is an is-a relationship. That is, we use inheritance only if there exists an is-a relationship between two classes
       - Car is a Vehicle
       - Orange is a Fruit
     - If the subclass and superclass have the same method, do the `@override`
       - **Overriding**: https://docs.oracle.com/javase/tutorial/java/IandI/override.html
         - The overriding method has the same name, number and type of parameters, and return type as the method that it overrides.
     - `super` Keyword:
       - used to call the method of the parent class from the method of the child class.
       - https://www.programiz.com/java-programming/super-keyword
     - In Java, if a class includes `protected` fields and methods, then these fields and methods are accessible from the subclass of the class.
   - (4) **Polymorphism**
     - Polymorphism allows us to perform a single action in different ways.
     - In other words, polymorphism allows you to define one interface and have multiple implementations.
     - **2 types**:
       - (1) Compile-time Polymorphism
         - Static polymorphism. This type of polymorphism is achieved by ***function overloading***
           - **Overloading**:
             - Same name
             - Different signatures
               - the number of parameters
               - data types of the parameters
               - the order of the parameters
           - https://www.geeksforgeeks.org/method-overloading-in-java/#:~:text=Important%20Questions%20in%20Java
       - (2) Runtime Polymorphism
         - Dynamic Method Dispatch.
         - It is a process in which a function call to the overridden method is resolved at Runtime.
         - This type of polymorphism is achieved by ***Method Overriding***

---

3. **List VS Set**
   - Both `List` (an interface) and `Set` (an interface) are members of `Java Collection` (an interface).
   - Differences:
     - A `List` can contain duplicates, but a `Set` can’t
     - A `List` will preserve the order of insertion, but a `Set` is not guaranteed to preserve the order
     - Since insertion order may not be maintained in a `Set`, it doesn’t allow index-based access as in the `List`
       - **Note** that there are a few implementations of the `Set` interface that maintain order, for example, `LinkedHashSet`.

---
4. **ArrayList vs LinkedList**, how is ArrayList internally implemented, and how is the linked list internally implemented?
  - **ArrayList**:
    - Internally, use **dynamic array**
      - A Dynamic array automatically grows when we try to make an insertion and there is no more space left for the new item. 
    - Act as a list only because it implements `List` only
    - Better for storing and assessing data
  - **LinkedList**:
    - Internally, use doubly linked list
    - Act as a list and queue both because it implements `List` and `Deque` interfaces
    - Better for manipulating data

---

5. What is **profiling**?
- In software engineering, profiling ("program profiling", "software profiling") is a form of dynamic program analysis that measures, for example, the space (memory) or time complexity of a program, the usage of particular instructions, or the frequency and duration of function calls.
- Most commonly, profiling information serves to aid program optimization, and more specifically, performance engineering.
- Profiling is a way to capture a snapshot of a program’s resource usage, called a profile, and connect that snapshot to the codebase in a way that allows you to directly map resource usage to lines of code.
- Profiling is a type of dynamic analysis, meaning it measures the code as it runs.
- This means profiling allows you to get an accurate picture of how your code is behaving both locally and in production, including interactions with databases and other services.

---

6. **Override VS Overload**
   - 如果两个method的argument完全相同，只有返回值不同，算不算overload
     - NO!
   - **Overriding** occurs when the method signature is the same in the superclass and the child class.
   - **Overloading** occurs when two or more methods in the same class have the same name but different parameters
     - (1) Number of parameters
     - (2) Data type of parameters
     - (3) Order of data type of parameters

---

7. What are the data structures internally used to maintain a **hashmap**? How to insert a value into a hashmap?
   - Data Structure: `array` - each spot in the array is called a **bucket**
   - Steps:
      - (1) `hash(key)` to get the hash #
      - (2) `hash # % array.length` to get the index of the bucket
      - (3) iterate the corresponding entry for the given key, which is a singly linked list - close addressing/separate chaining

  - **Collison Control**:
    - (1) Separate Chaining (Close Addressing)
      - the element of each of the buckets is a single linked list
    - (2) Open Addressing
      - put the key-value pair into the "next" available bucket
      - How to define "next"?
        - Linear probing
        - Quadratic probing
        - Exponential probing
        - Hash again
      - **Challenge**: handling removed keys in the map
        - https://www.cs.emory.edu/~cheung/Courses/253/Syllabus/Map/open-addr-delete.html#:~:text=Solving%20the%20deleting%20problem%20in%20Open%20Addressing
    
---

8. **Different Types of `Set`**
   - There are 3 general-purpose `Set` implementations — `HashSet`, `TreeSet`, and `LinkedHashSet`.
   - `HashSet` is much faster than `TreeSet` (constant-time versus log-time for most operations) but offers no ordering guarantees.
   - If you need to use the operations in the `SortedSet interface`, or if value-ordered iteration is required, use `TreeSet`; otherwise, use `HashSet`. It's a fair bet that you'll end up using `HashSet` most of the time.
   - `LinkedHashSet` is in some sense intermediate between `HashSet` and `TreeSet`.
     - Implemented as a hash table with a linked list running through it, it provides insertion-ordered iteration (least recently inserted to most recently) and runs nearly as fast as HashSet. The `LinkedHashSet` implementation spares its clients from the unspecified, generally chaotic ordering provided by `HashSet` without incurring the increased cost associated with `TreeSet`.

  - **Comparison**:
     - `HashSet`, which stores its elements in a hash table, is the best-performing implementation; however, it makes no guarantees concerning the order of iteration.
     - `TreeSet`, which stores its elements in a red-black tree, orders its elements based on their values; it is substantially slower than `HashSet`.
     - `LinkedHashSet`, which is implemented as a hash table with a linked list running through it, orders its elements based on the order in which they were inserted into the set (insertion-order). `LinkedHashSet` spares its clients from the unspecified, generally chaotic ordering provided by HashSet at a cost that is only slightly higher.

--- 
9. **Exception handling**

<img width="357" alt="Screenshot 2023-11-23 at 10 25 44 AM" src="https://github.com/sxw022020/Interview-Preparation/assets/60712169/7c9e3207-e7a6-48be-9a8c-240eb060de2f">

<img width="630" alt="Screenshot 2023-11-26 at 6 59 11 PM" src="https://github.com/sxw022020/Interview-Preparation/assets/60712169/59e99986-22f4-4841-8bdc-5b692c18890b">


- `Class Throwable`
- `Class Error`:
  - Usually beyond the control of the programmer, so we should not handle errors, such as stack overflow errors
- Exception Handling 2 ways:

<img width="592" alt="Screenshot 2023-11-23 at 10 35 29 AM" src="https://github.com/sxw022020/Interview-Preparation/assets/60712169/f4250ba0-b0df-402b-8f5a-716268c09f1a">

  - (1) Exceptions other than `RuntimeException`
    - Must use either:
      - <i> 接锅： `try/catch` block
      - <ii> 甩锅: `throws`
        - https://docs.google.com/document/d/1yVIKKNj03MOqfvqHYLDZAr9bQNuvdYvr6c-ux4_Mx9Y/edit#bookmark=id.my2abpf89r2v
  - (2) `RuntimeException`

---

10. 如何实现**multi-threading**
    - Udemy
   
---

11. **Java 8 new features**
    - (1) `Interface`:
      - Before: have only `public abstract methods`
      - After: can have `static` and `default` methods that have a defined behavior.
        - `static` method: available only through and inside of an interface. It can’t be overridden by an implementing class.
          ```Java
          static String producer() {
              return "N&F Vehicles";
          }
          ```
          - https://www.geeksforgeeks.org/static-method-in-interface-in-java/
        - `default` method: These are accessible through the instance of the implementing class and can be overridden.
          ```Java
          default String getOverview() {
              return "ATV made by " + producer();
          }
          ```
    - (2) `Stream` API for bulk data operations on `Collections`
      - Java’s Stream API to filter items of a collection based on a defined condition.
    - (3) Lambda exressions

---

12. `try-catch-finally`
    - https://docs.oracle.com/javase/tutorial/essential/exceptions/finally.html
    - Could have the following patterns as well:
      - `try-catch`
      - `try-finally`
    - https://docs.oracle.com/javase/tutorial/essential/exceptions/tryResourceClose.html

---

13. `finalize()` 的用法，为何需要它，不需要它行不行
    - Answer: 可以不要他, 因为已经deprecated
    - https://www.codingninjas.com/studio/library/finalize-method-in-java
    - https://www.codingninjas.com/studio/library/finalize-method-in-java#:~:text=Why%20finalize()%20method%20in%20Java%20is%20used%3F

---

14. Synchronized HashMap 和 non-Synchronized HashMap 区别
    - ?
    - https://stackoverflow.com/questions/1291836/concurrenthashmap-vs-synchronized-hashmap

---

15. 详细解释RestFul API‍‌‌‍‌‍‌‌‌‍‍‌‌‍‍‌‌‌‍‌是什么，如何进行通行的。为何它可以取代servelet?
    - ?

---
   
16. Static 的作用
    - https://www.geeksforgeeks.org/static-keyword-java/
    - https://www.geeksforgeeks.org/static-methods-vs-instance-methods-java/

---

17. Class VS Object
    - https://www.javatpoint.com/difference-between-object-and-class
    - https://www.geeksforgeeks.org/difference-between-class-and-object/

---

18. 用`static`修饰的ArrayList能不能添加元素
   - https://stackoverflow.com/questions/37351235/how-to-add-elements-to-java-static-arraylist
19. 用`final`修饰的ArrayList能不能添加元素
  - https://stackoverflow.com/questions/10750791/what-is-the-sense-of-final-arraylist

---

20. **shallow copy / deep copy**
    - https://www.cs.utexas.edu/~scottm/cs307/handouts/deepCopying.htm
    - https://www.geeksforgeeks.org/difference-between-shallow-and-deep-copy-of-a-class/

---

21. `final` 和 `static` 能不能同时出现
    - Yes, the final and static keywords can be used together in Java to declare a constant variable.
    - `public static final double PI = 3.14159;`
---

22. `String` VS `StringBuilder`
    - https://www.educative.io/answers/what-is-the-difference-between-string-and-stringbuilder-in-java

---

23. 几种方法实现multithreading
    - Udemy

---

24. JVM
    - https://www.javatpoint.com/jvm-java-virtual-machine
    - https://www.guru99.com/java-virtual-machine-jvm.html

---

25. **Transaction**
    - A transaction is any operation that is treated as a single unit of work, which either completes fully or does not complete at all, and leaves the storage system in a consistent state.
    - Transactional support allows applications to ensure the following:
      - All the steps to complete a logical unit of work are followed.
      - When one of the steps to the unit of work files fails, all the work done as part of that logical unit of work can be undone and the database can return to its previous state before the transaction began.
    - **ACID**: 4 key properties
      - Atomicity
        - Each statement in a transaction (to read, write, update, or delete data) is treated as a single unit
      - Consistency
        - Consistency guarantees that changes made within a transaction are consistent with database constraints. This includes all rules, constraints, and triggers. If the data gets into an illegal state, the whole transaction fails.
      - Isolation
        - When multiple users are reading and writing from the same table all at once, isolation of their transactions ensures that the concurrent transactions don't interfere with or affect one another
      - Durability
        - Durability guarantees that once the transaction completes and changes are written to the database, they are persisted. This ensures that data within the system will persist even in the case of system failures like crashes or power outages.

---

26. 为什么用redis，不用MySQL
    - Redis:
      - NoSQL - "not only SQL"
      - key-value pair
      - Unlike traditional databases, In-memory data stores don’t require a trip to disk, reducing engine latency to microseconds
      - Redis has a vast variety of data structures to meet your application needs.
      - Redis employs a primary-replica architecture and supports asynchronous replication where data can be replicated to multiple replica servers.
     
---

27. **Data Persistence**
    - When data is persisted, it means that the exact same data can be retrieved by an application when it’s opened again. The data remains the same, and nothing is lost in between sessions.

### SQL
1. SQL: inner join vs cross join
   - Inner Join:
     - Combines two or more records but displays only matching values in both tables.
       ```SQL
       SELECT column_name(s) FROM table1
       INNER JOIN table2
       ON table1.column_name = table2.column_name;
       ```
   - Cross Join:
     - Cross join is defined as a Cartesian product where the number of rows in the first table is multiplied by the number of rows in the second table.
       ```SQL
       Select *
       From table 1
       Cross join table2;
       ```
       <img width="754" alt="Screenshot 2023-11-23 at 8 19 21 PM" src="https://github.com/sxw022020/Interview-Preparation/assets/60712169/2642b313-f850-4369-806a-23e1eafeb3dc">

---

2. SQL: `WHERE` vs `HAVING`
   - The `WHERE` clause evaluates all rows, and the `HAVING` clause evaluates rows after they’ve been aggregated in the data set.
     ```SQL
     SELECT first_name, last_name FROM Customer WHERE first_name = 'john';
     ```
       - Return all the results
         
     ```SQL
     SELECT first_name, last_name FROM Customer GROUP BY last_name HAVING last_name = 'smith';
     ```
       - If all your customers have the last name of “smith” and “doe,” the SQL data set will only contain two rows with a count for the number of customers grouped in the row.
       - The `HAVING` clause filters grouped records. SQL has several aggregate functions, but `HAVING` is coupled with `GROUP BY`. As an example, the SQL statement above filters the above statement and only returns grouped records where the last name is “smith”
         - Only return 1 row: It has chosen one entry arbitrarily (or based on some internal logic of the database engine) for the group "Smith".

           <img width="736" alt="Screenshot 2023-11-26 at 9 53 45 PM" src="https://github.com/sxw022020/Interview-Preparation/assets/60712169/44a8236f-f3b8-4af9-a814-feff6cb174ed">

---

3. **NoSQL and SQL**
   - https://www.mongodb.com/nosql-explained/nosql-vs-sql#differences-between-sql-and-nosql
