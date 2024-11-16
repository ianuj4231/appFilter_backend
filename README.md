# Ecommerce_Fashion_Products_filtering_backend

## Pre-requisites & steps to run:


1. Java 17 installation - `[https://www.oracle.com/in/java/technologies/downloads/#java21](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)`


2. Install eclipse IDE -  `https://www.eclipse.org/downloads/`

3. Download repo zip folder ![image](https://github.com/user-attachments/assets/7b583165-945c-4af9-914d-26fc0a360271)

4. Extract zip folder.
     
5. open eclipse ide.top left corner click on file -> open projects from file system

6. Click on directory ![image](https://github.com/user-attachments/assets/9d988b7e-cd57-4451-aff5-d116716b2650)

7.select folder that u want to import... and now it is imported in eclipse ide.

8. in demo / src/main/java -> click on  "com.fashion" package -> and then right click on class DemoApplicaiton.java  
   ![image](https://github.com/user-attachments/assets/e3003f3f-c5c2-43d1-afd2-3430b097847c)

10. Now the server code has started running..next we will need to start redis server.  

11. Redis installation : https://redis.io/docs/latest/operate/oss_and_stack/install/install-redis/

12. Once zip folder is  installed,  extract Redis folder.

13. once extracted, pls click on the folder.. and then  click on the top  seacrch bar select the location

   ![image](https://github.com/user-attachments/assets/05856b47-4954-4bb1-ac73-85a14701c739)


14. erase the location and type cmd to get a terminal opened

   ![image](https://github.com/user-attachments/assets/68bd07da-52b4-40aa-91f2-87968f03cf12)


15. type command `redis-server.exe`

  ![image](https://github.com/user-attachments/assets/04ae6a95-f204-4e1d-9cce-41dadc457752)

16. This starts the redis server.

![image](https://github.com/user-attachments/assets/f55bf6e8-cd5e-4bc4-9839-43ab150d2927)

17. Now since we have both the server code and redis server up and running , we can test if our endpoints are working   


## Few API Endpoints  (All endpoints use the GET method)

(if therevo are no products that satisfy the input criteria, an empty list will be returned)

**can copy/paste the below endpoints in the browser to get customized results**

1. Get the first page of products, sorted by rating in descending order:
`http://localhost:8080/api/v1/fashion/filter`

3. Filter products with size `S` and color `red`:
   `http://localhost:8080/api/v1/fashion/filter?size=S&color=red`

4. Filter products with size `XXL`, color `green`, and rating `3`:
   `http://localhost:8080/api/v1/fashion/filter?size=XXL&color=green&rating=3`

5. Filter products by brand `h&m` (URL-encoded `&` as `%26`), sorted by price:
   `http://localhost:8080/api/v1/fashion/filter?brand=h%26m&sortby=price`

6. Get the second page of products sorted by price:
   `http://localhost:8080/api/v1/fashion/filter?sortby=price&page=1`

7. Get the fourth page of products sorted by price:
   `http://localhost:8080/api/v1/fashion/filter?sortby=price&page=3`

8. Filter products with size `L`:
   `http://localhost:8080/api/v1/fashion/filter?size=L`

9. Filter products with size `M` and rating `4`:
   `http://localhost:8080/api/v1/fashion/filter?size=M&rating=4`

10. Get the second page of products, sorted by rating in ascending order:
   `http://localhost:8080/api/v1/fashion/filter?page=1&sortby=rating&sortdirection=asc`

    
## Assumptions made

1. Consistent data structures are used to remain uniform throughout the application. This uniformity ensures that operations like filtering, sorting, and retrieving data work error-free because the structure and type of data do not change unexpectedly.
2. The applicaiton assumes that there is a customer who can perform custom filtering criteria to refine the product search according to their needs.

## Potential improvements with more time

1. Pub/Sub Messaging for Cache Invalidation/Update: Utilize a Pub/Sub messaging pattern to automatically invalidate or update the cache when product data changes. For example, when an admin updates product details or adds a new item to the inventory, a message is published to a Redis channel that triggers cache invalidation or updates, ensuring that all app instances use fresh data.
2. RBAC (Role-Based Access Control): Implementing RBAC can enhance security by controlling which users or roles have permission to modify the cache or access specific data. For instance, only admin users can add, update, or delete product information, ensuring unauthorized users do not impact cache consistency.
3. Distributed Cache: Use distributed caching so multiple instances of the fashion app can share the same cached data, enhancing scalability and reducing database load. For example, if multiple users search for products, the distributed cache ensures all instances access the same cached results, speeding up response times and maintaining data consistency across servers.

## Design pattern

1. The Strategy pattern is used because it allows the fashion app to apply multiple criteria dynamically to filter data during runtime. The Strategy pattern helps create a flexible system where each filter criterion (like category, price range, size) is implemented as a separate strategy or class. These strategies can be combined to process the same dataset or repository.
2. This design makes it easy to add, modify, or remove filters without changing the main code structure, promoting open/closed principle—the code is open for extension but closed for modification since u can add  extra filtering criteria without having to modify existing filterCriteria files. It is also extendable.
 <a href="https://github.com/ianuj4231/appFilter_backend/tree/main/src/main/java/com/fashion/service/filter">Markdown Guide</a>.
## Caching Using Redis Key-Value Pair

1. Caching in this fashion app is implemented using Redis, where data is stored as key-value pairs. Each key is a unique string composed of the filter criteria (a serialized string representation of the strategies list) combined with the page number and page size to represent a specific filtered result. The value stored is a custom object containing the filtered list of products. This allows quick retrieval of frequently requested data, reducing the need for repeated database queries and improving response time for paginated product listings.

## Centralized/Global exception handling using @ControllerAdvice

1. Centralized Error Handling: Using @ControllerAdvice allows to handle exceptions in one central place rather than in every individual method of services or controllers. This makes your code cleaner and more maintainable. When an exception occurs, @ControllerAdvice can catch it globally and provide a consistent error response to the client.

2. Instead of manually catching exceptions in every service method or controller (which leads to repetitive code and makes maintenance harder), global exception handling allows to reduce Redundancy - You don't need to write repetitive try-catch blocks in each method.

3. Easier Maintenance: If we need to change the way errors are handled (for example, modifying the error format ), we can do so in one place, without modifying each individual method.

4. Global exception handling keeps your **code clean**, **reduces redundancy**, and makes it easier to manage errors across the entire application.
