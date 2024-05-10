import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


public class Main {
    public static void main(String[] args) {
        List<Person> people = GetPeople();

//        OldWay(people);
//        NewWayFL(people);
//        print(Filter1(people));
//        print(Filter2(people));
        print(Filter3(people));
    }
    public static List<Person> NewWayFL(List<Person> people) {
        List<Person> floridians = people.stream().filter
                (person -> person.getState().equals("FL"))
                        .collect(Collectors.toList());
        return floridians;
    }

    public static List<Person> Filter1(List<Person> people) {
        List<Person> filteredPeeps = people
                .stream()
                .filter(person -> person.getName().matches("^[A-E].*$") || person.getEmail().matches("^.*(\\.net)$"))
                .collect(Collectors.toList());
        return filteredPeeps;
    }

    public static List<Person> Filter2(List<Person> people) {
        List<Person> filteredPeeps = people
                .stream()
                .filter(person -> person.getState().equals("CA") || person.getState().equals("NY"))
                .sorted(Comparator.comparing(Person::getPhone))
                .collect(Collectors.toList());
        return filteredPeeps;
    }

    public static List<Person> Filter3(List<Person> people) {
        List<Person> filteredPeeps = people
                .stream()
                .filter(person -> person.getEmail().matches("^.*q.*$"))
                .sorted(Comparator.comparing(Person::getName))
                .collect(Collectors.toList());
        return filteredPeeps;
    }

    public static void OldWay(List<Person> people) {
        List<Person> floridians = new ArrayList<>();
        for (Person person : people) {
            if (person.getState().equals("FL")) {
                if (person.getState().equals("FL")) {
                    floridians.add(person);
                }
                System.out.println(person.getName());
            }
        }
    }

    public static List<Person> GetPeople() {
        ObjectMapper mapper = new ObjectMapper();
        URL url = null;
        Person[] personArray = null;

        try {
            url = new URL("file:lambda.people.json");
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        }

        try {
            personArray = mapper.readValue(url, Person[].class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        List<Person> people = new ArrayList<>(Arrays.asList(personArray));
        return people;
    }

    public static void print(List<Person> people) {
        for (Person person : people) {
            System.out.println(person.getName() + " " + person.getEmail() + " " + person.getPhone());
        }
    }
}
