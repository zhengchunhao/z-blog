package com.zch.systerm.service;


import com.zch.systerm.entity.Menu;
import com.zch.systerm.entity.Route;

import java.util.List;

public interface RouteService {

    List<Route> getRoutes();

    List<Route> getAllRoutes(Menu menu);

}
