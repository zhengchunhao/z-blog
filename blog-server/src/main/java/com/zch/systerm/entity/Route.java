package com.zch.systerm.entity;

import java.util.List;

public class Route {

    private  String name;
    private  String router;

    private  String  component;

    private  String redirect;

    private  Meta meta;

    private  String path;

    private boolean invisible;

    private List<Route> children;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRouter() {
        return router;
    }

    public void setRouter(String router) {
        this.router = router;
    }

    public String getComponent() {
        return component;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getRedirect() {
        return redirect;
    }

    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public List<Route> getChildren() {
        return children;
    }

    public void setChildren(List<Route> children) {
        this.children = children;
    }

    public boolean isInvisible() {
        return invisible;
    }

    public void setInvisible(boolean invisible) {
        this.invisible = invisible;
    }
}
