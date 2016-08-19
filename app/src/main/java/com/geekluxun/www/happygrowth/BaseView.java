package com.geekluxun.www.happygrowth;

public interface BaseView<T extends BasePresenter> {

    void setPresenter(T presenter);
}
