package com.example.admin.fragment.Model;

public class Book extends Publication {

    private String _Author;
    private String _Publisher;

    public Book(){
    }
    public Book(String _Title, String _Author, String _Publisher)
    {
        this._Title = _Title;
        this._Author = _Author;
        this._Publisher = _Publisher;
    }
    public Book(int _Id, String _Title, String _Author, String _Publisher) {
        super(_Id, _Title);
        this._Author = _Author;
        this._Publisher = _Publisher;
    }

    public String Author() {
        return _Author;
    }

    public void Author(String _Author) {
        this._Author = _Author;
    }

    public String Publisher() {
        return _Publisher;
    }

    public void Publisher(String _Publisher) {
        this._Publisher = _Publisher;
    }


}
