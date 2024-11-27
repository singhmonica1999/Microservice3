# **Progress Tracking Service**

## **About** 

This microservice is a part of project, '**MediaNest**' - It conveys the idea of a cozy, organized space (a "nest") where users can collect, manage, and track all their favorite media—movies, series, books, and games—in one place. 

It monitors and updates the user’s progress for media items (movies, series, games, and books), tracking their completion status and providing real-time updates to ensure seamless media consumption tracking across platforms.

- **Features**:

  - **Track Media Progress**: Allow users to track their progress on movies, series, games, and books (e.g., episodes watched, chapters read, etc.).
  - **Update Progress**: Users can mark progress, such as marking episodes or chapters as "watched", "played", or "read".
  - **Progress Summary**: Provide users with a summary of their progress across different types of media (e.g., how many books they’ve read, how many episodes they've watched).

- **Endpoints**:

  - **POST /progress**: Creates or updates the progress for a specific media item for a user.
  - **GET /progress/{userId}**: Retrieves all progress data for a given user, showing the completion status of various media items.
  -  **GET /progress/{userId}/{mediaId}**: Retrieves the progress of a specific media item for a given user.
  - **PUT /progress/{userId}/{mediaId}**: Updates the progress of a specific media item for a user (e.g., if they are halfway through a book or episode).
  - **DELETE /progress/{userId}/{mediaId}**: Deletes the progress record for a specific media item for a given user.

  These endpoints allow the **Progress Tracking Service** to handle the creation, retrieval, updating, and deletion of progress data, ensuring the user’s media consumption is properly tracked.

  ----

  ## **API Specification**

  ------

  ### **1. POST /progress**

  - **Description**: Creates or updates the progress for a specific media item for a user.

  - **Request Body**:

    ```json
    {
      "user_id": "10001",
      "media_id": "12345",
      "progress_percentage": 45,
      "last_watched": "2024-11-27T10:00:00Z"
    }
    ```

    - `user_id`: Unique identifier of the user (integer or string).
    - `media_id`: Unique identifier of the media item (integer or string).
    - `progress_percentage`: Percentage of completion of the media (integer from 0 to 100).
    - `last_watched`: Timestamp indicating the last time the user interacted with the media.

  - **Response**:

    - **Status**: 201 Created
    - **Body**:

    ```json
    {
      "message": "Progress created/updated successfully",
      "progress_id": "56789"
    }
    ```

  - **Validations**:

    - `user_id` and `media_id` should exist in their respective tables (referenced from the Media Management and User Management Services).
    - `progress_percentage` should be between 0 and 100.
    - `last_watched` should be a valid ISO 8601 timestamp.

  ------

  ### **2. GET /progress/{userId}**

  - **Description**: Retrieves all progress data for a given user, showing the completion status of various media items.

  - **Path Parameter**:

    - `userId`: The unique identifier of the user.

  - **Response**:

    - **Status**: 200 OK
    - **Body**:

    ```json
    [
      {
        "media_id": "12345",
        "progress_percentage": 45,
        "last_watched": "2024-11-27T10:00:00Z",
        "media_title": "Avengers: Endgame",
        "media_type": "Movie"
      },
      {
        "media_id": "67890",
        "progress_percentage": 75,
        "last_watched": "2024-11-25T15:30:00Z",
        "media_title": "The Witcher",
        "media_type": "Series"
      }
    ]
    ```

  - **Validations**:

    - `userId` must exist in the User Management Service.

  ------

  ### **3. GET /progress/{userId}/{mediaId}**

  - **Description**: Retrieves the progress of a specific media item for a given user.

  - **Path Parameters**:

    - `userId`: The unique identifier of the user.
    - `mediaId`: The unique identifier of the media item.

  - **Response**:

    - **Status**: 200 OK
    - **Body**:

    ```json
    {
      "media_id": "12345",
      "progress_percentage": 45,
      "last_watched": "2024-11-27T10:00:00Z",
      "media_title": "Avengers: Endgame",
      "media_type": "Movie"
    }
    ```

  - **Validations**:

    - `userId` must exist in the User Management Service.
    - `mediaId` must exist in the Media Management Service.

  ------

  ### **4. PUT /progress/{userId}/{mediaId}**

  - **Description**: Updates the progress of a specific media item for a user (e.g., if they are halfway through a book or episode).

  - **Path Parameters**:

    - `userId`: The unique identifier of the user.
    - `mediaId`: The unique identifier of the media item.

  - **Request Body**:

    ```json
    {
      "progress_percentage": 60,
      "last_watched": "2024-11-28T12:00:00Z"
    }
    ```

    - `progress_percentage`: The updated percentage of completion of the media (integer from 0 to 100).
    - `last_watched`: Timestamp indicating the last time the user interacted with the media.

  - **Response**:

    - **Status**: 200 OK
    - **Body**:

    ```json
    {
      "message": "Progress updated successfully"
    }
    ```

  - **Validations**:

    - `userId` must exist in the User Management Service.
    - `mediaId` must exist in the Media Management Service.
    - `progress_percentage` should be between 0 and 100.
    - `last_watched` should be a valid ISO 8601 timestamp.

  ------

  ### **5. DELETE /progress/{userId}/{mediaId}**

  - **Description**: Deletes the progress record for a specific media item for a given user.

  - **Path Parameters**:

    - `userId`: The unique identifier of the user.
    - `mediaId`: The unique identifier of the media item.

  - **Response**:

    - **Status**: 200 OK
    - **Body**:

    ```json
    {
      "message": "Progress deleted successfully"
    }
    ```

  - **Validations**:

    - `userId` must exist in the User Management Service.
    - `mediaId` must exist in the Media Management Service.

  ------

  ### Common Response Codes

  - **200 OK**: Successful request (retrieval or update).
  - **201 Created**: A new progress record was successfully created.
  - **400 Bad Request**: Invalid request parameters (e.g., missing required fields, incorrect data types).
  - **404 Not Found**: When the user or media item does not exist.
  - **500 Internal Server Error**: Server error while processing the request.

  ------

  ### **Message Queue Integration**

  The **Progress Tracking Service** will publish messages to relevant message queues for updates to user progress:

  1. **Queue**: `media-progress-update`

     - **Producer**: Progress Tracking Service
     - **Payload**:

     ```json
     {
       "user_id": "10001",
       "media_id": "12345",
       "progress_percentage": 45,
       "last_watched": "2024-11-27T10:00:00Z"
     }
     ```

  2. **Queue**: `user-media-preference-update`

     - **Producer**: Progress Tracking Service (when updating user preferences related to media consumption).
     - **Payload**:

     ```json
     {
       "user_id": "10001",
       "media_id": "12345",
       "progress_percentage": 45
     }
     ```

  ------

  This API design ensures that progress tracking is handled efficiently while maintaining a clear separation of concerns between the **Progress Tracking Service** and other microservices.

  ----

  Here's the database schema for the **Progress Tracking Service**, including tables for tracking user progress across various media types (movies, series, books, and games).

  ### **1. Progress Table**

  This table stores the user's progress for each media item (movie, series, game, or book). It tracks the percentage of completion and the last interaction timestamp for each user-media pair.

  ```sql
  CREATE TABLE progress (
      progress_id INT AUTO_INCREMENT PRIMARY KEY,
      user_id INT NOT NULL,                            -- Foreign key reference to the User Management service
      media_id INT NOT NULL,                           -- Foreign key reference to the Media Management service
      progress_percentage INT NOT NULL CHECK (progress_percentage >= 0 AND progress_percentage <= 100),
      last_watched TIMESTAMP NOT NULL,                 -- Timestamp when the user last interacted with the media
      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  -- Timestamp when the progress record was created
      updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, -- Timestamp when the progress was last updated
      FOREIGN KEY (user_id) REFERENCES user_management_service.users(user_id),  -- Referencing users table from User Management service
      FOREIGN KEY (media_id) REFERENCES media_management_service.media(media_id) -- Referencing media table from Media Management service
  );
  ```

  - **Columns**:
    - `progress_id`: Unique identifier for the progress record (auto-incremented).
    - `user_id`: The ID of the user (foreign key from the **User Management Service**).
    - `media_id`: The ID of the media item (foreign key from the **Media Management Service**).
    - `progress_percentage`: The percentage of completion (an integer between 0 and 100).
    - `last_watched`: The timestamp of the last time the user interacted with the media item.
    - `created_at`: The timestamp when the record was created.
    - `updated_at`: The timestamp of the last update to the progress record.
  - **Foreign Key Constraints**:
    - `user_id` references the `user_id` from the **User Management Service**'s `users` table.
    - `media_id` references the `media_id` from the **Media Management Service**'s `media` table.

  ------

  ### **2. Indexes**

  To optimize query performance for frequently accessed queries, especially for user-specific media progress tracking, consider adding indexes.

  ```sql
  -- Index to quickly fetch a user's progress on all media
  CREATE INDEX idx_user_progress ON progress(user_id);
  
  -- Index to quickly fetch a specific user's progress on a media item
  CREATE INDEX idx_user_media_progress ON progress(user_id, media_id);
  ```

  ------

  ### Summary of the Schema:

  1. **`progress`** table stores the user’s progress (completion percentage and timestamp of the last interaction) for each media item.
  2. **Indexes** are used to optimize lookups by `user_id` and `media_id`.

  This schema ensures that the **Progress Tracking Service** can efficiently track and update the user’s media consumption progress, while being flexible enough to scale across different media types.

  ----

  ## **How to Setup the Project**

  **Dependencies**

  - Java 17+
  - Maven
  - MySQL
  - MQ

  

  **Steps**

  1. Clone the repository
  2. Execute: mvn clean install
  3. Setup database using the db-setup.sql
  4. Run the jar file.

  ----

  ## **Contributors**

  Thanks to the following contributors for their efforts on this project:

  [![Contributors](https://contrib.rocks/image?repo=[singhmonica1999](https://github.com/singhmonica1999)/Microservice3)](https://github.com/[singhmonica1999](https://github.com/singhmonica1999/Microservice3/graphs/contributors))