package itsgosho.domain.models.binding.thread;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ThreadCreateBindingModel {

    @NotNull
    @NotEmpty
    @Length(min = 3,max = 50)
    private String title;

    @NotNull
    @NotEmpty
    private String content;

    private Integer rowsForNewsPage;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getRowsForNewsPage() {
        return rowsForNewsPage;
    }

    public void setRowsForNewsPage(Integer rowsForNewsPage) {
        this.rowsForNewsPage = rowsForNewsPage;
    }
}
