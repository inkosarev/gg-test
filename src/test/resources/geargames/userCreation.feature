Feature: User Creation
  Тестирование создания пользователей через API

  Scenario: Создание одного пользователя
    Given POST-запрос на создание пользователя с телом:
      """
      [
        {
          "id": 0,
          "username": "geargames",
          "email": "gear@games.com",
          "password": "gear_games"
        }
      ]
      """
    Then Статус ответа для создания одного пользователя должен быть 200
    And Ответ должен содержать поле "id"

  Scenario: Создание нескольких пользователей
    Given POST-запрос на создание нескольких пользователей с телом:
      """
      [
        {
          "id": 0,
          "username": "geargames1",
          "email": "gear1@games.com",
          "password": "gear_games1"
        },
        {
          "id": 1,
          "username": "geargames2",
          "email": "gear2@games.com",
          "password": "gear_games2"
        }
      ]
      """
    Then Статус ответа для создания нескольких пользователей должен быть 200