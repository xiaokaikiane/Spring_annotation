package HMX.bean.Controller;

import HMX.bean.Service.bookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class bookController {
    @Autowired
    private bookService bookService;

}
