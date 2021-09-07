package models;

import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import lombok.Data;
import org.apache.commons.lang3.RandomStringUtils;

import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.lang.String.format;

@Data
public class User {
    Integer id;
    String name;
    String username;
    String email;
    List<Address> address;
    String phone;
    String website;
    List<Company> company;


    private static final ISettingsFile dataFile
            = new JsonSettingsFile(Paths.get("data", "auctions", "TestUser5.json").toString());

    private static String getData(String path) {
        return dataFile.getValueOrDefault(path, "").toString();
    }

    public static User readFromConfiguration() {
        User user = new User();
        Address address = new Address();
        user.setName("/name");
        user.setUsername("/username");
        user.setEmail("/email");
        List<Address> addressList = new ArrayList<>();
        address.setStreet("/street");
        address.add("/suite");
        address.add("/city");
        address.add("/zipcode");

//        item.setLabel(setRandomIfJsonEmpty("/Name",
//                format("TestItem%s_%s", RandomStringUtils.randomNumeric(NAME_DIGITS_COUNT_10), namesFormattedDate.format(new Date()))));
//        item.setAuctionName(setRandomIfJsonEmpty("/Auction Name",
//                format("TestAuction%s_%s", RandomStringUtils.randomNumeric(NAME_DIGITS_COUNT_10), namesFormattedDate.format(new Date()))));
//        item.setQuantity(getData("/Quantity"));
//        item.setUnit(getData("/Unit"));
//        item.setVersion(getData("/Version"));
//        item.setDocumentTitleAuction(setRandomIfJsonEmpty("/Document Title",
//                format("TestDocumentAuction%s_%s", RandomStringUtils.randomNumeric(NAME_DIGITS_COUNT_10), namesFormattedDate.format(new Date()))));
//        item.setDocumentTitleRFx(setRandomIfJsonEmpty("/Document Title",
//                format("TestDocumentRFx%s_%s", RandomStringUtils.randomNumeric(NAME_DIGITS_COUNT_10), namesFormattedDate.format(new Date()))));
//        item.setSupplier(getData("/Supplier"));
//        item.setMainContact(getData("/Main Contact"));
//        item.setMessageLabel(setRandomIfJsonEmpty("/Message Label",
//                format("TestLabel%s_%s", RandomStringUtils.randomNumeric(NAME_DIGITS_COUNT_10), namesFormattedDate.format(new Date()))));
//        item.setMessageText(setRandomIfJsonEmpty("/Message Text",
//                format("TestText%s_%s", RandomStringUtils.randomNumeric(NAME_DIGITS_COUNT_10), namesFormattedDate.format(new Date()))));
//        item.setLotName(setRandomIfJsonEmpty("/Lot Name",
//                format("TestLot%s_%s", RandomStringUtils.randomNumeric(NAME_DIGITS_COUNT_10), namesFormattedDate.format(new Date()))));
//        item.setLotDisplayOrder(getData("/Lot Display Order"));
//        item.setLotStartingPrice(getData("/Lot Starting Price"));
//        item.setLotType(getData("/Lot Type"));
        return item;
    }
}
