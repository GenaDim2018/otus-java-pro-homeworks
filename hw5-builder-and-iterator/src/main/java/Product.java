public class Product {
    private final String id;
    private final String title;
    private final String description;
    private final String cost;
    private final String weight;
    private final String width;
    private final String length;
    private final String height;

    public static Builder builder() {
        return new Builder();
    }

    private Product(Builder builder) {
        id = builder.id;
        title = builder.title;
        description = builder.description;
        cost = builder.cost;
        weight = builder.weight;
        width = builder.width;
        length = builder.length;
        height = builder.height;
    }

    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", cost='" + cost + '\'' +
                ", weight='" + weight + '\'' +
                ", width='" + width + '\'' +
                ", length='" + length + '\'' +
                ", height='" + height + '\'' +
                '}';
    }


    public static final class Builder {
        private String id;
        private String title;
        private String description;
        private String cost;
        private String weight;
        private String width;
        private String length;
        private String height;

        public Product build() {return new Product(this);}
        public Builder id(String id) {
            this.id = id;
            return this;
        }
        public Builder title(String title) {
            this.title = title;
            return this;
        }
        public Builder description(String description) {
            this.description = description;
            return this;
        }
        public Builder cost(String cost) {
            this.cost = cost;
            return this;
        }

        public Builder weight(String weight) {
            this.weight = weight;
            return this;
        }
        public Builder width(String width) {
            this.width = width;
            return this;
        }

        public Builder length(String length) {
            this.length = length;
            return this;
        }
        public Builder height(String height) {
            this.height = height;
            return this;
        }
    }

}
