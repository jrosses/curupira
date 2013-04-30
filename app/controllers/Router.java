package controllers;

import play.mvc.Controller;
import play.mvc.Result;

public class Router extends Controller
{
	public static Result publico(String route) 
	  {
		  return ok(views.html.routing.public1.render());
	  }
	
	public static Result authenticated(String route) 
	  {
		  return ok(views.html.routing.public1.render());
	  }
	
	public static Result admin(String route) 
	  {
		  return ok(views.html.routing.public1.render());
	  }
}
