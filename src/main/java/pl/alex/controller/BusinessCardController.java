package pl.alex.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.AllArgsConstructor;
import pl.alex.model.Address;
import pl.alex.model.Person;
import pl.alex.service.PersonService;

@Controller
@RequestMapping("person")
@AllArgsConstructor
public class BusinessCardController {

	@Autowired
	private final PersonService personService;

	@GetMapping("/list")
	public String getPersonList(Model model) {
		List<Person> personList = personService.getAllPerson();
		model.addAttribute("personList", personList);
		return "list";
	}

	@GetMapping("details/{personUUid}")
	public String getPerson(@PathVariable("personUUid") UUID personUUid, Model model) {
		model.addAttribute("person", personService.getPerson(personUUid));
		return "details";
	}

	@GetMapping("/add")
	public String addPerson(Model model) {
		final Person person = Person.builder().address(Address.builder().build()).build();
		model.addAttribute("person", person);
		return "form";
	}

	@PostMapping("/add")
	public String addPerson(Person person) {
		personService.save(person);
		return "redirect:/person/list";
	}

	@GetMapping("/edit/{personUUid}")
	public String editPerson(@PathVariable("personUUid") UUID personUUid, Model model) {
		model.addAttribute("person", personService.getPerson(personUUid));
		model.addAttribute("action", "/person/edit/"+personUUid);
		return "form";
	}

	@PostMapping("/edit/{personUUid}")
	public String editPerson(@PathVariable("personUUid") UUID personUUid, Person person) {
		personService.edit(personUUid, person);
		return "redirect:/person/list";
	}
	
	@GetMapping("/delete/{personUUid}")
	public String deletePerson(@PathVariable("personUUid") UUID personUUid) {
		personService.delete(personUUid);
		return "redirect:/person/list";
	}

}
