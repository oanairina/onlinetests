package converter;

import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import pojo.Controller;
import pojo.Question;

@FacesConverter("questionConverter")
public class QuestionConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Controller controller = new Controller();
        List<Question> questionList = controller.getQuestions();
        Question question = null;
        for (Question q : questionList) {
            if (q.getText().equals(value)) {
                question = q;
                break;
            }
        }
        return question;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return value.toString();
    }
}
