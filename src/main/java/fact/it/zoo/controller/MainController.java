package fact.it.zoo.controller;

// Jef Keppens
// r0885867

import fact.it.zoo.model.Staff;
import fact.it.zoo.model.Visitor;
import fact.it.zoo.model.Zoo;
import fact.it.zoo.model.AnimalWorld;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


@Controller
public class MainController{

    private ArrayList<Staff> staffArrayList;
    private ArrayList<Visitor> visitorArrayList;
    private ArrayList<Zoo> zooArrayList;


    @PostConstruct
    private void startup() {
        staffArrayList = this.fillStaffMembers();
        visitorArrayList = this.fillVisitors();
        zooArrayList = this.fillZoos();
    }


    @RequestMapping("/1_visitor")
    public String visitor(Model model) {

        model.addAttribute("zooarray", zooArrayList);
        model.addAttribute("guidelist", staffArrayList);

        return "1_visitor";
    }

    @RequestMapping("3_staff")
    public String staff() {
        return "3_staff";
    }

    @RequestMapping("2_visitormessage")
    public String visitorMessage(HttpServletRequest request, Model model) {
        String firstName = request.getParameter("first_name");
        String surname = request.getParameter("surname");
        Integer yearOfBirth = Integer.parseInt(request.getParameter("year"));

        Visitor visitor = new Visitor(firstName, surname);
        visitor.setYearOfBirth(yearOfBirth);
        zooArrayList.get(Integer.parseInt(request.getParameter("zooName"))).registerVisitor(visitor);
        visitorArrayList.add(visitor);
        model.addAttribute("visitor", visitor);

        return "2_visitormessage";
    }

    @RequestMapping("0_exam")
    public String exam(HttpServletRequest request, Model model) {
        String firstName = request.getParameter("first_name");
        String surname = request.getParameter("surname");
        int yearOfBirth = Integer.parseInt(request.getParameter("year"));
        int staffIndex = Integer.parseInt(request.getParameter("guide"));
        if (staffIndex < 0) {
            model.addAttribute("errormessage", "You didn't choose a guide!");
            return "error";
        }

        Visitor visitor = new Visitor(firstName, surname);
        visitor.setYearOfBirth(yearOfBirth);
        visitor.setGuide(staffArrayList.get(staffIndex));
        zooArrayList.get(Integer.parseInt(request.getParameter("zooName"))).registerVisitor(visitor);
        visitorArrayList.add(visitor);
        model.addAttribute("visitor", visitor);
        model.addAttribute("guide", staffArrayList.get(staffIndex));

        return "0_exam";
    }

    @RequestMapping("0_visitor")
    public String examVisitor(HttpServletRequest request, Model model) {
        int visitorIndex = Integer.parseInt(request.getParameter("visitorIndex"));

        model.addAttribute("visitor", visitorArrayList.get(visitorIndex));
        model.addAttribute("guide", visitorArrayList.get(visitorIndex).getGuide());

        return "0_exam";
    }

    @RequestMapping("4_staffmessage")
    public String staffMessage(HttpServletRequest request, Model model) {
        String firstName = request.getParameter("first_name");
        String surname = request.getParameter("surname");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse(request.getParameter("date"), dtf);
        boolean workingStudent = (request.getParameter("working") != null);

        Staff staff = new Staff(firstName, surname);
        staff.setStudent(workingStudent);
        staff.setStartDate(date);
        staffArrayList.add(staff);
        model.addAttribute("staff", staff);

        return "4_staffmessage";
    }

    @RequestMapping("5_stafflist")
    public String staffmembers(Model model) {

        model.addAttribute("staffarray", staffArrayList);

        return "5_stafflist";
    }

    @RequestMapping("6_visitorlist")
    public String visitors(Model model) {

        model.addAttribute("visitorarray", visitorArrayList);

        return "6_visitorlist";
    }

    @RequestMapping("7_zoo")
    public String zoo() {
        return "7_zoo";
    }

    @RequestMapping("8_zoolist")
    public String zoolist(HttpServletRequest request, Model model) {
        String zooName = request.getParameter("zoo");

        Zoo zoo = new Zoo(zooName);
        zooArrayList.add(zoo);
        model.addAttribute("zoolist", zooArrayList);

        return "8_zoolist";
    }

    @RequestMapping("8_zoos")
    public String zoos(Model model) {

        model.addAttribute("zoolist", zooArrayList);

        return "8_zoolist";
    }

    @RequestMapping("9_animalWorld")
    public String animalWorld(Model model) {

        model.addAttribute("zoolist", zooArrayList);
        model.addAttribute("stafflist", staffArrayList);

        return "9_animalworld";
    }

    @RequestMapping("10_animalworldlist")
    public String animalworldlist(HttpServletRequest request, Model model) {
        String animalworldName = request.getParameter("animalworldName");
        Integer animalworldNumber = Integer.parseInt(request.getParameter("animalworldNumber"));
        String photo = request.getParameter("photo");
        Integer zooName = Integer.parseInt(request.getParameter("zooName"));
        Integer staffName = Integer.parseInt(request.getParameter("staffName"));

        if (zooName < 0) {
            model.addAttribute("errormessage", "You didn't choose a zoo!");
            return "error";
        }
        if (staffName < 0) {
            model.addAttribute("errormessage", "You didn't choose a staff member!");
            return "error";
        }



        AnimalWorld animalWorld = new AnimalWorld(animalworldName, animalworldNumber);
        animalWorld.setPhoto(photo);
        animalWorld.setResponsible(staffArrayList.get(staffName));

        zooArrayList.get(zooName).addAnimalWorld(animalWorld);
        model.addAttribute("zoo", zooArrayList.get(zooName));

        return "10_animalworldlist";
    }

    @RequestMapping("10_animalworldlistchosenzoo")
    public String openanimalworldzoo(HttpServletRequest request, Model model) {
        Integer zooName = Integer.parseInt(request.getParameter("zooName"));

        model.addAttribute("zoo", zooArrayList.get(zooName));

        return "10_animalworldlist";
    }

    @RequestMapping("11_animalworldinfo")
    public String search(HttpServletRequest request, Model model) {
        String result = request.getParameter("search");

        for (int i = 0; i < zooArrayList.size(); i++) {
            for (int j = 0; j < zooArrayList.get(i).getAnimalWorlds().size(); j++) {
                if (zooArrayList.get(i).getAnimalWorlds().get(j).getName().equals(result)) {
                    model.addAttribute("animalworld", zooArrayList.get(i).getAnimalWorlds().get(j));
                    return "11_animalworldinfo";
                }
            }
        }
        String errormessage = "There is no animal world with the name '" + result + "'";
        model.addAttribute("errormessage", errormessage);
        return "error";
    }





   private ArrayList<Staff> fillStaffMembers() {
        ArrayList<Staff> staffMembers = new ArrayList<>();

        Staff staff1 = new Staff("Johan", "Bertels");
        staff1.setStartDate(LocalDate.of(2002, 5, 1));
        Staff staff2 = new Staff("An", "Van Herck");
        staff2.setStartDate(LocalDate.of(2019, 3, 15));
        staff2.setStudent(true);
        Staff staff3 = new Staff("Bruno", "Coenen");
        staff3.setStartDate(LocalDate.of(1995,1,1));
        Staff staff4 = new Staff("Wout", "Dayaert");
        staff4.setStartDate(LocalDate.of(2002, 12, 15));
        Staff staff5 = new Staff("Louis", "Petit");
        staff5.setStartDate(LocalDate.of(2020, 8, 1));
        staff5.setStudent(true);
        Staff staff6 = new Staff("Jean", "Pinot");
        staff6.setStartDate(LocalDate.of(1999,4,1));
        Staff staff7 = new Staff("Ahmad", "Bezeri");
        staff7.setStartDate(LocalDate.of(2009, 5, 1));
        Staff staff8 = new Staff("Hans", "Volzky");
        staff8.setStartDate(LocalDate.of(2015, 6, 10));
        staff8.setStudent(true);
        Staff staff9 = new Staff("Joachim", "Henau");
        staff9.setStartDate(LocalDate.of(2007,9,18));
        staffMembers.add(staff1);
        staffMembers.add(staff2);
        staffMembers.add(staff3);
        staffMembers.add(staff4);
        staffMembers.add(staff5);
        staffMembers.add(staff6);
        staffMembers.add(staff7);
        staffMembers.add(staff8);
        staffMembers.add(staff9);
        return staffMembers;
    }

    private ArrayList<Visitor> fillVisitors() {
        ArrayList<Visitor> visitors = new ArrayList<>();
        Visitor visitor1 = new Visitor("Dominik", "Mioens");
        visitor1.setYearOfBirth(2001);
        visitor1.setGuide(staffArrayList.get(0));
        Visitor visitor2 = new Visitor("Zion", "Noops");
        visitor2.setYearOfBirth(1996);
        visitor2.setGuide(staffArrayList.get(1));
        Visitor visitor3 = new Visitor("Maria", "Bonetta");
        visitor3.setYearOfBirth(1998);
        visitor3.setGuide(staffArrayList.get(2));
        Visitor visitor4 = new Visitor("Jef", "Keppens");
        visitor4.setYearOfBirth(2003);
        visitor4.setGuide(staffArrayList.get(3));
        visitors.add(visitor1);
        visitors.add(visitor2);
        visitors.add(visitor3);
        visitors.add(visitor4);
        visitors.get(0).addToWishList("Dolphin");
        visitors.get(0).addToWishList("Snake");
        visitors.get(1).addToWishList("Lion");
        visitors.get(1).addToWishList("Eagle");
        visitors.get(1).addToWishList("Monkey");
        visitors.get(1).addToWishList("Elephant");
        visitors.get(2).addToWishList("Turtle");
        visitors.get(3).addToWishList("Dog");
        visitors.get(3).addToWishList("Monkey");
        return visitors;
    }

    private ArrayList<Zoo> fillZoos() {
        ArrayList<Zoo> zoos = new ArrayList<>();
        Zoo zoo1 = new Zoo("Antwerp Zoo");
        Zoo zoo2 = new Zoo("Bellewaerde");
        Zoo zoo3 = new Zoo("Plankendael Zoo");
        AnimalWorld animalWorld1 = new AnimalWorld("Aquarium");
        AnimalWorld animalWorld2 = new AnimalWorld("Reptiles");
        AnimalWorld animalWorld3 = new AnimalWorld("Monkeys");
        animalWorld1.setNumberOfAnimals(1250);
        animalWorld2.setNumberOfAnimals(500);
        animalWorld3.setNumberOfAnimals(785);
        animalWorld1.setPhoto("/img/aquarium.jpg");
        animalWorld2.setPhoto("/img/reptiles.jpg");
        animalWorld3.setPhoto("/img/monkeys.jpg");
        animalWorld1.setResponsible(staffArrayList.get(0));
        animalWorld2.setResponsible(staffArrayList.get(1));
        animalWorld3.setResponsible(staffArrayList.get(2));
        zoo1.addAnimalWorld(animalWorld1);
        zoo1.addAnimalWorld(animalWorld2);
        zoo1.addAnimalWorld(animalWorld3);
        zoo2.addAnimalWorld(animalWorld1);
        zoo2.addAnimalWorld(animalWorld2);
        zoo3.addAnimalWorld(animalWorld1);
        zoo3.addAnimalWorld(animalWorld3);
        zoos.add(zoo1);
        zoos.add(zoo2);
        zoos.add(zoo3);
        return zoos;
    }


}
