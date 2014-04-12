FORMAT: 1A
HOST: http://www.google.com

# Telephony
In adipisicing dreamcatcher nostrud freegan sapiente. Leggings ugh nostrud, 
veniam YOLO sint small batch Pitchfork retro meh eu esse flexitarian. Elit 
leggings pop-up swag, incididunt tousled fixie selvage distillery. 
Hashtag put a bird on it 3 wolf moon, keytar Tumblr forage velit butcher 
VHS stumptown Portland adipisicing officia chambray. Beard ethical 
fingerstache Etsy. Seitan selvage Vice, typewriter kale chips velit 
Tumblr incididunt sustainable kogi reprehenderit artisan Cosby 
sweater chillwave. Dolore disrupt nesciunt officia polaroid, master 
cleanse asymmetrical commodo mlkshk small batch bitters.

# Group Information
służy do obsługiwania żądań związanych z zarządzaniem sesją użytkownika

## Zwraca informacje na temat stanu systemu [/information/fetch]
### Generuje najswieższe najważniejsze statystyki dotyczące systemu. [POST]
+ Request (application/json)

        {
          "username": "adamnowak",
          "password": "790bdsifus62"
        }
        
+ Response 200 (application/json)

        {
        }
        
+ Response 400 (application/json)

        {
        }
        
+ Response 500 (application/json)

        {
        }
        
# Group Session
służy do obsługiwania żądań związanych z zarządzaniem sesją użytkownika

## Inicjalizacja sesji użytkownika [/session/initialize]
### Tworzona jest nowa sesja użytkownika. Jest ważna przez 30 minut. [POST]
+ Request (application/json)

        {
          "username": "adamnowak",
          "password": "790bdsifus62"
        }
        
+ Response 201 (application/json)

        {
          "sessionId" : "3dsf45dfs678adas90dada",
          "valid_until" : "2014-08-21 14:11:09"
        }
        
+ Response 400 (application/json)

        {
        }
        
+ Response 500 (application/json)

        {
        }

## Niszczenie sesji  [/session/destroy]
### Aktualna sesja zostaje nieaktywna. [DELETE]
+ Request (application/json)

        {
          "username" : "adamnowak",
          "sessionId" : "3dsf45dfs678adas90dada" 
        }

+ Response 204
        

+ Response 400 (application/json)

        {
        }
        
+ Response 500 (application/json)

        {
        }
        
## Odswieżanie sesji   [/session/refresh]        
### Aktualnie trwająca sesja zostaje wydłużona o kolejne 30 minut. [POST]
+ Request (application/json)

        {
          "username" : "adamnowak",
          "sessionId" : "3dsf45dfs678adas90dada" 
        }

+ Response 201 (application/json)

        { 
          "success": true
        }
        
+ Response 400 (application/json)

        {
        }
        
+ Response 500 (application/json)

        {
        }

## Walidacja sesji  [/session/validate]
### Zwraca informację czy przekazana sesja jest jeszcze aktywna. [POST]
+ Request (application/json)

        {
          "username" : "adamnowak",
          "sessionId" : "3dsf45dfs678adas90dada" 
        }

+ Response 200 (application/json)

        { 
          "is_valid": true
        }
        
+ Response 400 (application/json)

        {
        }
        
+ Response 500 (application/json)

        {
        }

# Group Users
służy do obsługiwania żądań związanych z zarządzaniem użytkownikami

## Pobieranie użytkowników [/users/fetch/{page}/{numberperpage}]
+ Parameters

    + page (integer) ...  page number
    + numberperpage (integer) ... number of elements per page

### Pobiera listę użytkowników według zadanych parametrów [POST]

+ Request (application/json)

        {
          "username" : "adamnowak",
          "sessionId" : "3dsf45dfs678adas90dada",
          "
        }

+ Response 201 (application/json)

        { 
        }
        
+ Response 400 (application/json)

        {
        }
        
+ Response 500 (application/json)

        {
        }
        
## Dodaj nowego użytkownika [/users/add]
### Dodaje nowego użytkownika, po uprzedniej walidacji. [POST]

+ Request (application/json)

        {
          "username" : "adamnowak",
          "sessionId" : "3dsf45dfs678adas90dada" 
        }

+ Response 201 (application/json)

        { 
          
        }  
        
+ Response 400 (application/json)

        { 
          
        }


+ Response 500 (application/json)

        { 
          
        }

## Usuwanie użytkownika [/users/remove/{id}]

+ Parameters

    + id (integer) ... unikalny identyfikator uzytkownika

### Usuwa użytkownika  [DELETE]

+ Request (application/json)

        {
          "username" : "adamnowak",
          "sessionId" : "3dsf45dfs678adas90dada" 
        }

+ Response 204

+ Response 400 (application/json)

        {
        }
        
+ Response 500 (application/json)

        {
        }
        
## Edytowanie użytkownika [/users/edit/{id}]

+ Parameters

    + id (integer) ... unikalny identyfikator użytkownika
    
### Edytuje informacje użytkownika. [POST]

+ Request (application/json)

        {
          "username" : "adamnowak",
          "sessionId" : "3dsf45dfs678adas90dada" 
        }

+ Response 201 (application/json)

        { 
          
        }

+ Response 400 (application/json)

        {
        
        }
        
+ Response 500 (application/json)

        {
        
        }
        
        

## Pobieranie ról [/users/{id}/roles/fetch]
+ Parameters

    + id (integer) ...  unikalny identyfikator użytkownika

### Pobiera kolekcję uprawnień użytkownika [POST]

+ Request (application/json)

        {
          "username" : "adamnowak",
          "sessionId" : "3dsf45dfs678adas90dada" 
        }

+ Response 200 (application/json)

        { 
          
        }
        
+ Response 400 (application/json)

        { 
          
        }


+ Response 500 (application/json)

        { 
          
        }

        
## Usuwanie roli użytkownikowi [/users/{id}/roles/remove]
+ Parameters

    + id (integer) ...  unikalny identyfikator użytkownika

### Usuwa wskazaną rolę użytkownika. [POST]

+ Request (application/json)

        {
          "username" : "adamnowak",
          "sessionId" : "3dsf45dfs678adas90dada" 
        }

+ Response 204

+ Response 400 (application/json)

        { 
          
        }


+ Response 500 (application/json)

        { 
          
        }

        
## Dodawanie roli użytkownikowi [/users/{id}/roles/add]
+ Parameters

    + id (integer) ...  unikalny identyfikator użytkownika

### Dodaje użytkownikowi rolę. [POST]

+ Request (application/json)

        {
          "username" : "adamnowak",
          "sessionId" : "3dsf45dfs678adas90dada" 
        }

+ Response 201

+ Response 400 (application/json)

        { 
          
        }


+ Response 500 (application/json)

        { 
          
        }
        
## Modyfikowanie danych użytkownika [/users/{id}/edit]

+ Parameters 

    + id (integer) ... unikalny identyfikator użytkownika
    
### Modyfikuje dane użytkownika [POST]

+ Request (application/json)

        {
          "username" : "adamnowak",
          "sessionId" : "3dsf45dfs678adas90dada" 
        }

+ Response 201

+ Response 400 (application/json)

        { 
          
        }


+ Response 500 (application/json)

        { 
          
        }
        

# Group Stores
służy do obsługiwania żądań związanych z zarządzaniem magazynami
        
## Pobieranie produktów sklepu [/stores/{id}/products/{page}/{numberperpage}]

+ Parameters

    + id (integer) ... unikalny identyfikator magazynu
    + page (integer) ...  numer strony
    + numberperpage (integer) ... liczba elementów na stronie

### Pobiera listę produktów według zadanych parametrów. [POST]

+ Request (application/json)

        {
          "username" : "adamnowak",
          "sessionId" : "3dsf45dfs678adas90dada",
          "query" : "name=asd&catgory=foo"
        }

+ Response 201 (application/json)

        { 
          
        }
        
+ Response 400 (application/json)

        { 
          
        }


+ Response 500 (application/json)

        { 
          
        }

## Dodawanie magazynu [/stores/add]

### Dodaje nowy magazyn. [POST]

+ Request (application/json)

        {
          "username" : "adamnowak",
          "sessionId" : "3dsf45dfs678adas90dada" 
        }

+ Response 200 (application/json)

        { 
          
        }
        
+ Response 400 (application/json)

        { 
          
        }


+ Response 500 (application/json)

        { 
          
        }

## Pobieranie magazynów [/stores/fetch/{page}/{numberperpage}]

+ Parameters

    + page (integer) ...  numer strony
    + numberperpage (integer) ... liczba elementów na stronie

### Pobiera kolekcję magazynów według zadanych parametrów. [POST]

+ Request (application/json)

        {
          "username" : "adamnowak",
          "sessionId" : "3dsf45dfs678adas90dada" 
        }

+ Response 201 (application/json)

        { 
          
        }
        
## Modyfikowanie magazynu [/stores/edit/{id}]

+ Parameters

    + id (integer) ...  unikalny identyfikator magazynu
    
### Modyfikuje dane o magazynie. [POST]

+ Request (application/json)

        {
          "username" : "adamnowak",
          "sessionId" : "3dsf45dfs678adas90dada" 
        }

+ Response 201 (application/json)

        { 
          
        }
        
+ Response 400 (application/json)

        { 
          
        }


+ Response 500 (application/json)

        { 
          
        }

        
## Usuwanie magazynu [/stores/remove/{id}]

+ Parameters

    + id (integer) ...  unikalny identyfikator magazynu
    
### Usuwa wskazany magazyn. [DELETE]

+ Request (application/json)

        {
          "username" : "adamnowak",
          "sessionId" : "3dsf45dfs678adas90dada" 
        }

+ Response 201 (application/json)

        { 
          
        }
        
+ Response 400 (application/json)

        { 
          
        }


+ Response 500 (application/json)

        { 
          
        }
        
# Group Deliveries
służy do obsługiwania żądań związanych z zarządzaniem dostawami towarów

## Pobieranie dostaw [/deliveries/store/{id}/fetch/{page}/{numberperpage}]

+ Parameters

    + id  (integer) ... unikalny identyfikator magazynu
    + page (integer) ... numer strony
    + numberperpage (integer) ... liczba elementów na stronie
    
### Pobiera kolekcję dostaw według zadanych parametrów [POST]

+ Request (application/json)

        {
          "username" : "adamnowak",
          "sessionId" : "3dsf45dfs678adas90dada" 
        }

+ Response 200 (application/json)

        { 
          
        }

+ Response 400 (application/json)

        { 
          
        }


+ Response 500 (application/json)

        { 
          
        }

## Dodawanie dostawy [/deliveries/store/{id}/add]

+ Parameters 
    
    + id (integer) ... unikanly identyfikator magazynu

### Rejestruje nową dostawę [POST]

+ Request (application/json)

        {
          "username" : "adamnowak",
          "sessionId" : "3dsf45dfs678adas90dada" 
        }

+ Response 201 (application/json)

        { 
          
        }
        
+ Response 400 (application/json)

        { 
          
        }


+ Response 500 (application/json)

        { 
          
        }
        
## Modyfikowanie dostawy [/deliveries/{id}/edit]

+ Parameters
    
    + id (integer) ... unikalny identyfikator dostawy

### Modyfikuje szczegóły dostawy [POST]

+ Request (application/json)

        {
          "username" : "adamnowak",
          "sessionId" : "3dsf45dfs678adas90dada" 
        }

+ Response 201 (application/json)

        { 
          
        }
        
+ Response 400 (application/json)

        { 
          
        }


+ Response 500 (application/json)

        { 
          
        }
        
## Anulowanie dostawy [/deliveries/{id}/remove]

+ Parameters
    
    + id (integer) ... unikalny identyfikator dostawy

### Usuwanie szczegółów dostawy [POST]

+ Request (application/json)

        {
          "username" : "adamnowak",
          "sessionId" : "3dsf45dfs678adas90dada" 
        }

+ Response 204 

+ Response 400 (application/json)

        { 
          
        }


+ Response 500 (application/json)

        { 
          
        }
        
# Group Products 
służy do obsługiwania żądań związanych z zarządzaniem produktkami

## Pobieranie szczegółów produktu [/products/{id}/fetchDetails]

+ Parameters 

    + id (integer) ... unikalny identyfikator produktu
    
### Pobiera wszystkie informacje o produkcie [POST]

+ Request (application/json)

        {
          "username" : "adamnowak",
          "sessionId" : "3dsf45dfs678adas90dada"
        }
        
+ Response 200 (application/json)

        { 
          
        }
        
+ Response 400 (application/json)

        { 
          
        }


+ Response 500 (application/json)

        { 
          
        }
        
## Modyfikowanie cennika produktu [/products/{id}/editPrice]

+ Parameters 

    + id (integer) ... unikalny identyfikator produktu
    
### Modyfikuje szczegóły cennika produktu [POST]

+ Request (application/json)

        {
          "username" : "adamnowak",
          "sessionId" : "3dsf45dfs678adas90dada"
        }
        
+ Response 201 (application/json)

        { 
          
        }
        
+ Response 400 (application/json)

        { 
          
        }


+ Response 500 (application/json)

        { 
          
        }
        
## Modyfikowanie opodatkowania produktu [/products/{id}/editTax]

+ Parameters 

    + id (integer) ... unikalny identyfikator produktu
    
### Modyfikuje szczegóły opodatkowania produktu [POST]

+ Request (application/json)

        {
          "username" : "adamnowak",
          "sessionId" : "3dsf45dfs678adas90dada"
        }
        
+ Response 201 (application/json)

        { 
          
        }
        
+ Response 400 (application/json)

        { 
          
        }


+ Response 500 (application/json)

        { 
          
        }
        
## Modyfikowanie informacji o produkcie [/products/{id}/edit]

+ Parameters 

    + id (integer) ... unikalny identyfikator produktu
    
### Modyfikuje informacje o produkcie [POST]

+ Request (application/json)

        {
          "username" : "adamnowak",
          "sessionId" : "3dsf45dfs678adas90dada"
        }
        
+ Response 201 (application/json)

        { 
          
        }
        
+ Response 400 (application/json)

        { 
          
        }


+ Response 500 (application/json)

        { 
          
        }

# Group Sales
służy do obsługiwania żądań związanych z zarządzaniem użytkownikami

## Pobierz listę sprzedaży [/sales/store/{id}/fetch/{page}/{numberperpage}]

+ Parameters 
    
    + id (integer) ... unikalny identyfikator magazynu
    + page (integer) ... numer strony
    + numberperpage (integer) ... liczba elementów na stronie

### Pobiera listę sprzedaży według zadanych parametrów. [POST]

+ Request (application/json)

        {
          "username" : "adamnowak",
          "sessionId" : "3dsf45dfs678adas90dada",
          "query" : "asd=qwe&foo=bar"
        }

+ Response 200 (application/json)

        { 
          
        }
        
+ Response 400 (application/json)

        { 
          
        }


+ Response 500 (application/json)

        { 
          
        }
        
## Dodawanie sprzedaż [/sales/store/{id}/add]

+ Parameters 

    + id (integer) ... unikalny identyfikator magazynu
    
### Rejestruje sprzedaż w systemie [POST]

+ Request (application/json)

        {
          "username" : "adamnowak",
          "sessionId" : "3dsf45dfs678adas90dada" 
        }

+ Response 201 (application/json)

        { 
          
        }

+ Response 400 (application/json)

        { 
          
        
        }


+ Response 500 (application/json)

        { 
          
        }
        
        
## Anulowanie sprzedaży [/sales/remove/{id}]

+ Parameters
    
    + id (integer) ... unikalny identyfikator sprzedaży
    
### Usuwa wskazaną sprzedaż [POST]

+ Request (application/json)

        {
          "username" : "adamnowak",
          "sessionId" : "3dsf45dfs678adas90dada" 
        }

+ Response 204

+ Response 400 (application/json)

        { 
          
        }


+ Response 500 (application/json)

        { 
          
        }
        
# Group Contacts
służy do obsługiwania żądań związanych z zarządzaniem użytkownikami

## Pobierz listę [/contacts/fetch/{page}/{numberperpage}]
+ Parameters

    + page (integer) ...  page number
    + numberperpage (integer) ... number of elements per page

### Pobiera listę kontaktów według zadanych parametrów [POST]

+ Request (application/json)

        {
          "username" : "adamnowak",
          "sessionId" : "3dsf45dfs678adas90dada",
          "query" : "asd=foo&bar=qwe"
        }

+ Response 201 (application/json)

        { 
          
        }

## Dodawanie kontaktu [/contacts/add]
### Dodaje nowy kontakt. [POST]

+ Request (application/json)

        {
          "username" : "adamnowak",
          "sessionId" : "3dsf45dfs678adas90dada" 
        }

+ Response 201 (application/json)

        { 
          
        }
        
## Modyfikowanie kontaktu [/contacts/edit/{id}]

+ Parameters

    + id  (integer) ... unikalny identyfikator kontakt
    
### Modyfikuje kontakt o wsazanym id [POST]

+ Request (application/json)

        {
          "username" : "adamnowak",
          "sessionId" : "3dsf45dfs678adas90dada" 
        }

+ Response 201 (application/json)

        { 
          
        }
        
+ Response 400 (application/json)

        { 
          
        }


+ Response 500 (application/json)

        { 
          
        }


## Usuwanie kontaktu [/contacts/remove/{id}]
+ Parameters

    + id (integer) ...  unikalny identyfikator kontakt

### Usuwa kontakt o wskazanym id [DELETE]

+ Request (application/json)

        {
          "username" : "adamnowak",
          "sessionId" : "3dsf45dfs678adas90dada" 
        }

+ Response 204
        
+ Response 400 (application/json)

        { 
          
        }


+ Response 500 (application/json)

        { 
          
        }

# Group Complaints 
służy do obsługiwania żądań związanych ze skargami klientow

## Dodawanie skargi na produkt   [/complaints/product/{id}/add]

+ Parameters 

    + id (integer) ... unikalny identyfikator produktu
    
### Dodaje skargę o na temat produktu [POST]

+ Request (application/json)

        {
          "username" : "adamnowak",
          "sessionId" : "3dsf45dfs678adas90dada" 
        }

+ Response 204
        
+ Response 400 (application/json)

        { 
          
        }


+ Response 500 (application/json)

        { 
          
        }

## Dodawanie skargi na dostawę   [/complaints/delivery/{id}/]

+ Parameters 

    + id (integer) ... unikalny identyfikator dostawy
    
### Dodaje skargę na temat zrealizowanej dostawy [POST]

+ Request (application/json)

        {
          "username" : "adamnowak",
          "sessionId" : "3dsf45dfs678adas90dada" 
        }

+ Response 201 (application/json)

        {

        }
        
+ Response 400 (application/json)

        { 
          
        }


+ Response 500 (application/json)

        { 
          
        }

## Dodawanie skargi na sprzedaż  [/complaints/sale/{id}]

+ Parameters 

    + id (integer) ... unikalny identyfikator skargi
    
### Dodaje skargę na temat zrealizowanej sprzedaży. [POST]

+ Request (application/json)

        {
          "username" : "adamnowak",
          "sessionId" : "3dsf45dfs678adas90dada" 
        }

+ Response 201 (application/json)

        {

        }
        
+ Response 400 (application/json)

        { 
          
        }


+ Response 500 (application/json)

        { 
          
        }


## Dodawanie komentarza do skargi na produkt   [/complaints/product/{id}/comment/add]

+ Parameters 

    + id (integer) ... unikalny identyfikator skargi
    
### Dodaje komentarz do skargi. [POST]

+ Request (application/json)

        {
          "username" : "adamnowak",
          "sessionId" : "3dsf45dfs678adas90dada" 
        }

+ Response 201 (application/json)
    
        {
        
        }
        
+ Response 400 (application/json)

        { 
          
        }


+ Response 500 (application/json)

        { 
          
        }

## Dodawanie komentarza do skargi na dostawę   [/complaints/delivery/{id}/comment/add]

+ Parameters 

    + id (integer) ... unikalny identyfikator skargi
    
### Dodaje komentarz do skargi [POST]

+ Request (application/json)

        {
          "username" : "adamnowak",
          "sessionId" : "3dsf45dfs678adas90dada" 
        }

+ Response 201 (application/json)

        {
        
        }
        
+ Response 400 (application/json)

        { 
          
        }


+ Response 500 (application/json)

        { 
          
        }

## Dodawanie komentarza do skargi na sprzedaż  [/complaints/sale/{id}/comment/add]

+ Parameters 

    + id (integer) ... unikalny identyfikator skargi
    
### Dodaje komentarz do skargi [POST]

+ Request (application/json)

        {
          "username" : "adamnowak",
          "sessionId" : "3dsf45dfs678adas90dada" 
        }

+ Response 201 (application/json)

        {
        
        }
        
+ Response 400 (application/json)

        { 
          
        }


+ Response 500 (application/json)

        { 
          
        }
        
# Group Files
służy do obsługiwania żądań zwiazanych z plikami 

## Pobieranie informacji o plikach [/files/fetch/{page}/{numberperpage}]

+ Parameters 

    + page (integer) ... numer strony 
    
    + numberperpage (integer) ... liczba elementów na stronie

### Pobiera listę plików w kolekcji wedle zadanych parametrów [POST]


+ Request (application/json)

        {
          "username" : "adamnowak",
          "sessionId" : "3dsf45dfs678adas90dada",
          "query" : "asd=foo&bar=qwe"
        }

+ Response 200 (application/json)

        {
        
        }
        
+ Response 400 (application/json)

        { 
          
        }


+ Response 500 (application/json)

        { 
          
        }
        
## Dodawanie pliku [/files/add]
    
### Dodaje plik do kolekcji [POST]

+ Request (application/json)

        {
          "username" : "adamnowak",
          "sessionId" : "3dsf45dfs678adas90dada" 
        }

+ Response 201 (application/json)

        {
        
        }
        
+ Response 400 (application/json)

        { 
          
        }


+ Response 500 (application/json)

        { 
          
        }
        
## Modyfikowanie pliku [/files/{id}/edit/]
    
+ Parameters 

    + id (integer) ... unikalny identyfikator pliku
    
### Modyfikuje informacje o pliku [POST]

+ Request (application/json)

        {
          "username" : "adamnowak",
          "sessionId" : "3dsf45dfs678adas90dada" 
        }

+ Response 201 (application/json)

        {
        
        }
        
+ Response 400 (application/json)

        { 
          
        }


+ Response 500 (application/json)

        { 
          
        }
        
## Usuwanie pliku [/files/{id}/remove]

+ Parameters 
    
    + id (integer) ... unikalny identyfikator pliku

### Usuwa plik z kolekcji [POST]

+ Request (application/json)

        {
          "username" : "adamnowak",
          "sessionId" : "3dsf45dfs678adas90dada" 
        }

+ Response 204
        
+ Response 400 (application/json)

        { 
          
        }


+ Response 500 (application/json)

        { 
          
        }
        
## Dowiązanie pliku z produktem [/files/{fileid}/bind/product/{id}]

+ Parameters 
    
    + fileid (integer) ... unikalny identyfikator pliku
    + id (integer) ... unikalny identyfikator produktu

### Dowiązuje plik z produktem [POST]

+ Request (application/json)

        {
          "username" : "adamnowak",
          "sessionId" : "3dsf45dfs678adas90dada" 
        }

+ Response 201 (application/json)

        {
        
        }
        
+ Response 400 (application/json)

        { 
          
        }


+ Response 500 (application/json)

        { 
          
        }

## Odwiązanie pliku z produktem [/files/{fileid}/unbind/product/{id}]

+ Parameters 
    
    + fileid (integer) ... unikalny identyfikator pliku
    + id (integer) ... unikalny identyfikator produktu

### Odwiązuje plik z produktem [POST]

+ Request (application/json)

        {
          "username" : "adamnowak",
          "sessionId" : "3dsf45dfs678adas90dada" 
        }

+ Response 204
        
+ Response 400 (application/json)

        { 
          
        }


+ Response 500 (application/json)

        { 
          
        }

## Dowiązanie pliku ze skargą [/files/{fileid}/bind/complaint/{id}]

+ Parameters 
    
    + fileid (integer) ... unikalny identyfikator pliku
    + id (integer) ... unikalny identyfikator skargi

### Dowiązuje plik ze skargą [POST]

+ Request (application/json)

        {
          "username" : "adamnowak",
          "sessionId" : "3dsf45dfs678adas90dada" 
        }

+ Response 201 (application/json)

        {
        
        }
        
+ Response 400 (application/json)

        { 
          
        }


+ Response 500 (application/json)

        { 
          
        }


## Odwiązanie pliku ze skargą [/files/{fileid}/unbind/complaint/{id}]

+ Parameters 
    
    + fileid (integer) ... unikalny identyfikator pliku
    + id (integer) ... unikalny identyfikator skargi

### Odwiązuje plik od skargi [POST]

+ Request (application/json)

        {
          "username" : "adamnowak",
          "sessionId" : "3dsf45dfs678adas90dada" 
        }

+ Response 204
        
+ Response 400 (application/json)

        { 
          
        }


+ Response 500 (application/json)

        { 
          
        }
