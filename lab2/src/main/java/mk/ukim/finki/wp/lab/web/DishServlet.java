package mk.ukim.finki.wp.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.Chef;
import mk.ukim.finki.wp.lab.sevice.ChefService;
import mk.ukim.finki.wp.lab.sevice.DishService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name = "DishServlet", urlPatterns = "/dish")
public class DishServlet extends HttpServlet {
    private final SpringTemplateEngine templateEngine;
    private final DishService dishService;
    private final ChefService chefService;

    public DishServlet(SpringTemplateEngine templateEngine, DishService dishService, ChefService chefService) {
        this.templateEngine = templateEngine;
        this.dishService = dishService;
        this.chefService = chefService;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(request, response);

        Long chefId = Long.parseLong(request.getParameter("chefId"));
        Chef chef = chefService.findById(chefId);

        WebContext context = new WebContext(webExchange);
        context.setVariable("dishes", dishService.listDishes());
        context.setVariable("chefId", chefId);
        context.setVariable("chefName", chef.getFirstName() + " " + chef.getLastName());

        templateEngine.process("dishesList.html", context, response.getWriter());
    }

}