package HMX.bean.Service;

import HMX.bean.dao.bookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class bookService {
    @Autowired
    private bookDao bookDao;
    public void print(){
        System.out.println(bookDao);
    }

    @Override
    public String toString() {
        return "bookService{" +
                "bookDao=" + bookDao +
                '}';
    }
}
