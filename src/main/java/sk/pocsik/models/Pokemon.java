package sk.pocsik.models;

import com.google.gson.annotations.SerializedName;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name = "pokemon")
@NoArgsConstructor
@RequiredArgsConstructor
public class Pokemon extends BaseEntity{
    @SerializedName("id")
    @Column(name = "api_id")
    private String apiId;
    @NonNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private String name;
    private Images images;
    private Cardmarket cardmarket;

    public String getSmallImage() {
        return this.images.getSmall();
    }
}

@Embeddable
@Data
class Cardmarket {
    private Prices prices;
}

@Embeddable
@Data
class Images {
    private String small;
    private String large;
}

@Embeddable
@Data
class Prices {
    private double averageSellPrice;
    private double lowPrice;
    private double trendPrice;
    private double germanProLow;
    private double suggestedPrice;
    private double reverseHoloSell;
    private double reverseHoloLow;
    private double reverseHoloTrend;
    private double lowPriceExPlus;
    private double avg1;
    private double avg7;
    private double avg30;
    private double reverseHoloAvg1;
    private double reverseHoloAvg7;
    private double reverseHoloAvg30;
}
