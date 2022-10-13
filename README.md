
# Recipes

[Recipes](https://hyperskill.org/projects/180) - challenging project from [HyperSkill](https://hyperskill.org/tracks/12)

# About
Spring Boot application with REST API 
created for posting recipes.

# Usage

Avaible without authorization:

### ```POST```: [http://localhost:8881/api/register/](http://localhost:8881/api/register/)
```json
{
    "email": "test.user@gmail.com",
    "password": "veryStrongP4ssword"
}
```

Available only to logged-in users: 
```json
authorization:
{
    "email": "test.user@gmail.com",
    "password": "veryStrongP4ssword"
}
```
### ```POST``` [http://localhost:8881/api/recipe/new](http://localhost:8881/api/recipe/new)
```json
{
    "name": "Fresh Mint Tea",
    "category": "beverage",
    "description": "Light, aromatic and refreshing beverage, ...",
    "ingredients": ["boiled water", "honey", "fresh mint leaves"],
    "directions": ["Boil water", "Pour boiling hot water into a mug", "Add fresh mint leaves", "Mix and let the mint leaves seep for 3-5 minutes", "Add honey and mix again"]
}
```
response:
```json
{
    "id": 1
}
```

### ```GET``` [http://localhost:8881/api/recipe/{id}](http://localhost:8881/api/recipe/1) to get the recipe
response:
```json
{
    "name": "Fresh Mint Tea",
    "description": "Light, aromatic and refreshing beverage, ...",
    "category": "beverage",
    "date": "2022-10-13T21:14:56.067389",
    "ingredients": [
        "boiled water",
        "honey",
        "fresh mint leaves"
    ],
    "directions": [
        "Boil water",
        "Pour boiling hot water into a mug",
        "Add fresh mint leaves",
        "Mix and let the mint leaves seep for 3-5 minutes",
        "Add honey and mix again"
    ]
}
```
### ```PUT```: [http://localhost:8881/api/recipe/{id}](http://localhost:8881/api/recipe/1) to update the recipe
```json
{
    "name": "Warming Ginger Tea",
    "category": "beverage",
    "description": "Ginger tea is a warming drink for cool weather, ...",
    "ingredients": ["1 inch ginger root, minced", "1/2 lemon, juiced", "1/2 teaspoon manuka honey"],
    "directions": ["Place all ingredients in a mug and fill with warm water (not too hot so you keep the beneficial honey compounds in tact)", "Steep for 5-10 minutes", "Drink and enjoy"]
}
```
### ```DELETE``` [http://localhost:8881/api/recipe/{id}](http://localhost:8881/api/recipe/1) to delete a recipe

without response


### ```GET``` [http://localhost:8881/api/recipe/search/?category={category}](localhost:8881/api/recipe/search?category=beverage) to search for recipes by category
### ```GET``` [http://localhost:8881/api/recipe/search/?name={name}](localhost:8881/api/recipe/search?name=localhost:8881/api/recipe/search?name=FreshMintTea) to search for recipes by name
sample response:
```json
[
    {
        "name": "Fresh Mint Tea",
        "description": "Light, aromatic and refreshing beverage, ...",
        "category": "beverage",
        "date": "2022-10-13T21:24:03.870111",
        "ingredients": [
            "boiled water",
            "honey",
            "fresh mint leaves"
        ],
        "directions": [
            "Boil water",
            "Pour boiling hot water into a mug",
            "Add fresh mint leaves",
            "Mix and let the mint leaves seep for 3-5 minutes",
            "Add honey and mix again"
        ]
    }
]
```
