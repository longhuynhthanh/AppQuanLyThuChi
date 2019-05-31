package com.example.admin.fragment.Model;

public class Newspaper extends Publication {
    private int _Count;

    public int Count() {
        return _Count;
    }

    public void Count(int _Count) {
        this._Count = _Count;
    }

    public Newspaper(){}
    public Newspaper(String _Title, int _Count)
    {
        this._Title = _Title;
        this._Count = _Count;
    }
    public Newspaper(int _Id, String _Title, int _Count) {
        super(_Id, _Title);
        this._Count = _Count;
    }
}
