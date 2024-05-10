public class Program {
    public static void main(String[] args) {
        Controller c = new Controller();
        System.out.println(c.isValidHumanName("Gurt Macklin"));
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
        String[] results = c.getHTMLTagsContents(html, "label");
        for (int i = 0; i < results.length; i++) {
            System.out.println(results[i]);
        }
    }
}
