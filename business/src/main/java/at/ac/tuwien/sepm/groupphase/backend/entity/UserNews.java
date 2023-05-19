package at.ac.tuwien.sepm.groupphase.backend.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;

@Entity
@Table(name = "user_news")
public class UserNews {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Schema(accessMode = AccessMode.READ_ONLY, name = "userId")
    private Long userId;

    @Schema(accessMode = AccessMode.READ_ONLY, name = "newsId")
    private Long newsId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNewsId() {
        return newsId;
    }

    public void setNewsId(Long newsId) {
        this.newsId = newsId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public static UserNewsBuilder builder() {
        return new UserNewsBuilder();
    }

    @Override
    public String toString() {
        return "User read News{" +
            "id=" + id +
            "userId=" + userId +
            ", newsId=" + newsId +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserNews that = (UserNews) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        return newsId != null ? !newsId.equals(that.newsId) : that.newsId != null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (newsId != null ? newsId.hashCode() : 0);
        return result;
    }


    public static final class UserNewsBuilder {
        private Long id;
        private Long userId;
        private Long newsId;

        private UserNewsBuilder() {
        }

        public UserNewsBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public UserNewsBuilder userId(Long userId) {
            this.userId = userId;
            return this;
        }

        public UserNewsBuilder newsId(Long newsId) {
            this.newsId = newsId;
            return this;
        }

        public UserNews build() {
            UserNews userNews = new UserNews();
            userNews.setId(id);
            userNews.setUserId(userId);
            userNews.setNewsId(newsId);
            return userNews;
        }
    }

}