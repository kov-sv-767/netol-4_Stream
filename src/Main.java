import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John", "Tomas", "Henry", "Anny");
        List<String> surnames = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown", "Smith");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    surnames.get(new Random().nextInt(surnames.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        //1. Найти количество несовершеннолетникх (возраст менее 18 лет)
        long count = persons.stream()
                .filter(person -> person.getAge() < 18)
                .count();
        System.out.println("Колиечество несовершеннолетних в списке: " + count + " чел.");

        //2. Получить список фамилий призывников (мужчин от 18 до 27 лет)
        List<String> recruit = persons.stream()
                .filter(person -> person.getSex() == Sex.MAN)
                .filter(person -> person.getAge() >= 18)
                .filter(person -> person.getAge() <= 27)
                .map(Person::getSurname)
                .collect(Collectors.toList());
        System.out.println("Список призывников:");
        System.out.println(recruit);

        //3. Получить отсортированный по фамилии список потенциально работоспособных
        //  людей с высшим образованием (женщины от 18 до 60 лет, мужчины до 65 лет)
        List<String> workable = persons.stream()
                .filter(person -> person.getEducation() == Education.HIGHER)
                .filter(person -> person.getAge() >= 18)
                .filter(person -> ((person.getSex() == Sex.MAN && person.getAge() < 65)) || ((person.getSex()) == Sex.WOMAN && person.getAge() < 60))
                .map(Person::getSurname)
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList());
        System.out.println("Список потенциально работоспособных людей с высшим образованием:");
        System.out.println(workable);


    }
}
