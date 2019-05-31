package com.example.admin.fragment.Model;

public class Publication {
    protected int _Id;
    protected String _Title;

    public int Id() {
        return _Id;
    }

    public void Id(int _Id) {
        this._Id = _Id;
    }

    public String Title() {
        return _Title;
    }

    public void Title(String _Title) {
        this._Title = _Title;
    }
    public Publication(){}
    public Publication(String _Title)
    {
        this._Title = _Title;
    }
    public Publication(int _Id, String _Title) {
        this._Id = _Id;
        this._Title = _Title;

    }
}
