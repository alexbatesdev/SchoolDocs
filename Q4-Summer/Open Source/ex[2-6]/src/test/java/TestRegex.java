import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class TestRegex {
    Controller c = new Controller();

    @Test
    void TestHumanName() {
        assertTrue(c.isValidHumanName("Mr. Gurt Macklin"));
        assertFalse(c.isValidHumanName(""));
    }

    @Test
    void TestEmail() {
        assertTrue(c.isValidEmailAddress("mcbuzzer@gmail.com"));
        assertFalse(c.isValidEmailAddress("7a@6.communism"));
    }

    @Test
    void TestPhone() {
        assertTrue(c.isValidPhoneNumber("385-202-9315"));
        assertFalse(c.isValidPhoneNumber("385-2032-9315"));
    }

    @Test
    void TestSSN() {
        assertTrue(c.isValidSSN("898-47-6746"));
        assertFalse(c.isValidSSN("(385)-202-9315"));
    }

    @Test
    void TestPassword() {
        assertTrue(c.validatePasswordComplexity("P@5$woRD5 Ar3 d()p3", 8, 3, 3, 3, 3));
        assertFalse(c.validatePasswordComplexity("Jeffrey Epstein did not kill himself", 8, 3, 3, 3, 3));
    }
    String html = """
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="js/script.js" defer></script>
    <link rel="stylesheet" href="css/style.css">
    <title>Form</title>
</head>

<body>
    <header></header>
    <section>
        <h1>Form</h1>
        <form id="myForm" method="post">
            <a href="www.youtube.com">LINK</a>
            <label>First Name: </label>
            <input type="text" placeholder="First Name" id="inFName">
            <label>Last Name: </label>
            <input type="text" placeholder="Last Name" id="inLName">
            <label>Email: </label>
            <input type="text" placeholder="Email" id="inEmail">

            <label>Address: </label>
            <input type="text" placeholder="123 N Breezy St." id="inAddr">

            <label>City: </label>
            <input type="text" placeholder="Arsenopolis" id="inCity">

            <label>State: </label>
            <input type="text" placeholder="Spain (Without the 'S')" id="inState">

            <label>Zip code: </label>
            <input type="text" placeholder="84102" id="inZip">

            <label>Phone: </label>
            <input type="text" placeholder="420-666-6969" id="inPhone">

            <a href="www.youtube.com">LINK</a>
            <label>Password: </label>
            <input type="password" placeholder="●●●●●●●●" id="inPass">

            <label>Verify Password: </label>
            <input type="password" placeholder="●●●●●●●●" id="inVPass">

            <input type="submit" value="Submit">
        </form>
    </section>
    <footer></footer>
</body>

</html>""";
    @Test
    void TestHTMLContents() {
        String[] expected = {"First Name: ", "Last Name: ","Email: ","Address: ","City: ","State: ","Zip code: ","Phone: ","Password: ", "Verify Password: "};
        assertEquals(Arrays.toString(expected), Arrays.toString(c.getHTMLTagsContents(html, "label")));
    }

    @Test
    void TestHTMLURL() {
        String[] expected = {"www.youtube.com","www.youtube.com"};
        assertEquals(Arrays.toString(expected), Arrays.toString(c.getHTMLLinkURL(html)));
    }
}
