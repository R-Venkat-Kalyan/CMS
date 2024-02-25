package com.example.cms.contoller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.cms.entity.CMSContact;
import com.example.cms.entity.CMSUsers;
import com.example.cms.entity.MappingDetails;
import com.example.cms.entity.ScheduleAppointment;
import com.example.cms.service.CMSContactService;
import com.example.cms.service.CMSUsersService;
import com.example.cms.service.MappingDetailsService;
import com.example.cms.service.ScheduledAppointmentsService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.server.PathParam;

@Controller
public class CmsController {

//	REQUIRED VARAIBLES
	int adminLogFlag = 0;
	int studentLogFlag = 0;
	int counselorLogFlag = 0;
	int idParam = 0;
	long studendtId = 0;

//	REQUIRED OBJECTS
	@Autowired
	private CMSUsersService cus;

	@Autowired
	private MappingDetailsService mds;

	@Autowired
	private ScheduledAppointmentsService sas;

	@Autowired
	private CMSContactService ccs;

//	START OF BASIC FUNCTIONS ROUTES !!

	@GetMapping("/")
	public String home() {
		return "Home1";
	}

	@PostMapping("/contact")
	public String contactForm(@ModelAttribute CMSContact cc) {
		ccs.addToDB(cc);
		return "redirect:/";
	}

//	END OF BASIC FUNCTION ROUTES !!

//	START OF LOGIN, LOGOUT && REGISTERATION ROUTES !!	

	@GetMapping("/login")
	public String loginForm() {
		return "Login_form";
	}

	@PostMapping("/user")
	public String roleBasedLogin(HttpServletRequest request, RedirectAttributes redirectAttributes) {
		long id = Long.parseLong(request.getParameter("id"));
		String password = request.getParameter("password");
		int idLen = request.getParameter("id").length();

		if (id == 1234 && password.equals("1234")) {
			adminLogFlag = 1;
			redirectAttributes.addFlashAttribute("successMessage", "Welcome Admin: " + id + " 👮‍♂️");
			return "redirect:/admin";
		}
		List<CMSUsers> users = cus.getAllUsers();
		for (CMSUsers user : users) {
			if (idLen == 4 && user.getId() == id && user.getPassword().equals(password)) {
				redirectAttributes.addFlashAttribute("successMessage", "Welcome Counselor: " + id + " 👨‍✈️");
				counselorLogFlag = 1;
				idParam = (int) id;
				return "redirect:/counselor";
			}
			if (idLen == 4 && user.getId() == id && !user.getPassword().equals(password)) {
				redirectAttributes.addFlashAttribute("successMessage", "Wrong password for: " + id);
				return "redirect:/register";
			}
			if (idLen == 10 && user.getId() == id && user.getPassword().equals(password)) {
				if (mds.checkUser(id)) {
					redirectAttributes.addFlashAttribute("successMessage", "Welcome Student: " + id + " 👨‍🎓");
					studentLogFlag = 1;
					studendtId = id;
					return "redirect:/student";
				} else {
					studentLogFlag = 1;
					redirectAttributes.addFlashAttribute("successMessage", "Welcome Student: " + id
							+ "\nYour Counseling details are not added yet !! We are working ont it..");
					return "redirect:/student";
				}
			}
			if (idLen == 10 && user.getId() == id && !user.getPassword().equals(password)) {
				redirectAttributes.addFlashAttribute("successMessage", "Wrong Password for: " + id);
				return "redirect:/register";
			}
		}
		redirectAttributes.addFlashAttribute("successMessage", "User Not Found ❌❌ Please Register First.");
		return "redirect:/register";
	}

	@GetMapping("/register")
	public String registerForm() {
		return "Register_form";
	}

	@PostMapping("/save")
	public String registerUsers(@ModelAttribute CMSUsers cu, RedirectAttributes redirectAttributes,
			HttpServletRequest request) {
		long id = Long.parseLong(request.getParameter("id"));
		if (cus.getByID(id)) {
			redirectAttributes.addFlashAttribute("successMessage", "User Already Exists✔ Please log in.");
			return "redirect:/register";
		} else {
			cus.storeToDB(cu);
			redirectAttributes.addFlashAttribute("successMessage", "Registration successful✔ Please log in.");
			return "redirect:/login";
		}
	}

	@GetMapping("/logout")
	public String logout() {
		adminLogFlag = 0;
		studentLogFlag = 0;
		counselorLogFlag = 0;
		return "redirect:/login";
	}

	@GetMapping("/error")
	public String errorPage() {
		return "Error";
	}

//	END OF LOGIN && REGISTERATION ROUTES !!	

//	START OF STUDENT PANEL FUNCTION ROUTES !!	

	@GetMapping("/student")
	public String studentDashboard() {
		if (studentLogFlag == 1)
			return "Student";
		else
			return "redirect:/";
	}

	@GetMapping("/myCounselor")
	public ModelAndView getMyCounselor(RedirectAttributes redirectAttributes) {
		if (studentLogFlag == 1) {
			List<MappingDetails> std = mds.getAllRecords();
			MappingDetails matchObj = null;
			for (MappingDetails obj : std) {
				if (obj.getStudentId() == studendtId) {
					matchObj = obj;
					break;
				}
			}
			if (matchObj == null) {
				redirectAttributes.addFlashAttribute("successMessage",
						"Sorry !! Your Institution didn't provide us your data..");
				return new ModelAndView("Student");
			} else {
				ModelAndView modelAndView = new ModelAndView("viewMyCounselor");
				modelAndView.addObject("mc", matchObj);
				return modelAndView;
			}
		} else
			return new ModelAndView("Error");
	}

	@GetMapping("/Appointment")
	public String scheduleForm() {
		if (studentLogFlag == 1)
			return "ScheduleAppointment";
		else
			return "redirect:/error";
	}

	@GetMapping("/yourChannel")
	public ModelAndView getYourChannel(RedirectAttributes redirectAttributes) {
		if (studentLogFlag == 1) {
			List<MappingDetails> std = mds.getAllRecords();
			MappingDetails matchObj = null;
			for (MappingDetails obj : std) {
				if (obj.getStudentId() == studendtId) {
					matchObj = obj;
					break;
				}
			}
			if(matchObj == null) {
				redirectAttributes.addFlashAttribute("successMessage",
						"Sorry !! Your Institution didn't provide us your data..");
				return new ModelAndView("Student");
			}
			ModelAndView modelAndView = new ModelAndView("telegramChannel");
			modelAndView.addObject("tc", matchObj);
			return modelAndView;
		} else
			return new ModelAndView("Error");
	}

	@PostMapping("/addschedule")
	public String addNewAppointment(@ModelAttribute ScheduleAppointment sa, RedirectAttributes redirectAttributes) {
		if (studentLogFlag == 1) {
			if (sas.appointmentExists(studendtId)) {
				redirectAttributes.addFlashAttribute("successMessage", "Appointment Schedule already exists. ❗❗");
				return "redirect:/student";
			}
			MappingDetails dm = mds.getById(studendtId);
			if(dm == null) {
				redirectAttributes.addFlashAttribute("successMessage",
						"Sorry !! Your Institution didn't provide us your data..");
				return "redirect:/student";
			}
			else {
			sa.setCounselorId(dm.getCounselorId());
			sa.setStudentId(studendtId);
			sas.storeAppointments(sa);
			redirectAttributes.addFlashAttribute("successMessage", "Appointment Scheduled successfully. ✔");
			return "redirect:/student";
			}
		} else
			return "redirect:/error";
	}

//	END OF STUDENT PANEL FUNCTION ROUTES !!	

//	START OF COUNSELOR PANEL FUNCTION ROUTES !!	

	@GetMapping("/counselor")
	public String counselorDashboard() {
		if (counselorLogFlag == 1)
			return "Counselor";
		else
			return "redirect:/error";
	}

	@GetMapping("/myStudents")
	public ModelAndView getYourStudents() {
		if (counselorLogFlag == 1) {
			List<MappingDetails> std = mds.getAllRecords();
			List<MappingDetails> std1 = new ArrayList<>();
			for (MappingDetails obj : std) {
				if (obj.getCounselorId() == idParam) {
					std1.add(obj);
				}
			}
			return new ModelAndView("myGroupStudents", "my", std1);
		} else
			return new ModelAndView("Error");
	}

	@GetMapping("/myAppointments")
	public ModelAndView getMyAppointments() {
		if (counselorLogFlag == 1) {
			List<ScheduleAppointment> apps = sas.getAllAppointments();
			List<ScheduleAppointment> myApps = new ArrayList<>();
			for (ScheduleAppointment obj : apps) {
				if (obj.getCounselorId() == idParam) {
					myApps.add(obj);
				}
			}
			return new ModelAndView("myAppointments", "ma", myApps);
		} else
			return new ModelAndView("Error");
	}

	@GetMapping("/myChannel")
	public ModelAndView getMyChannel() {
		if (counselorLogFlag == 1) {
			List<MappingDetails> std = mds.getAllRecords();
			MappingDetails matchObj = null;
			for (MappingDetails obj : std) {
				if (obj.getCounselorId() == idParam) {
					matchObj = obj;
					break;
				}
			}
			ModelAndView modelAndView = new ModelAndView("telegramChannel");
			modelAndView.addObject("tc", matchObj);
			return modelAndView;
		} else
			return new ModelAndView("Error");
	}

	@RequestMapping("/deleteAppointment/{studentId}")
	public String deleteAppointment(@PathVariable("studentId") long studentId, RedirectAttributes redirectAttributes) {
		if (counselorLogFlag == 1) {
			sas.deleteAppointment(studentId);
			redirectAttributes.addFlashAttribute("successMessage", "Appointment Deleted successfully. ✔");
			return "redirect:/counselor";
		} else
			return "redirect:/error";
	}

//	END OF COUNSELOR PANEL FUNCTION ROUTES !!

//	START OF ADMIN PANEL FUNCTION ROUTES !!

	@GetMapping("/admin")
	public String adminPanel() {
		if (adminLogFlag == 1)
			return "Admin";
		else
			return "redirect:/error";
	}

	@GetMapping("/addNewMapping")
	public String addMappingForm() {
		if (adminLogFlag == 1)
			return "AddMapping";
		else
			return "redirect:/error";

	}

	@GetMapping("/editMappings")
	public ModelAndView editMappings() {
		if (adminLogFlag == 1) {
			List<MappingDetails> allMappings = mds.getAllRecords();
			return new ModelAndView("ModifyMappings", "map1", allMappings);
		} else {
			return new ModelAndView("Error");
		}
	}

	@GetMapping("/allMappings")
	public ModelAndView getAllMappings() {
		if (adminLogFlag == 1) {
			List<MappingDetails> all = mds.getAllRecords();
			return new ModelAndView("mappinglist", "map", all);
		} else {
			return new ModelAndView("Error");
		}
	}

	@RequestMapping("/deleteMapping/{studentId}")
	public String deleteMapping(@PathVariable("studentId") long studentId) {
		if (adminLogFlag == 1) {
			mds.deleteMapping(studentId);
			return "redirect:/editMappings";
		} else
			return "redirect:/error";
	}

	@RequestMapping("/editMapping/{studentId}")
	public String editMapping(@PathVariable("studentId") long studentId, Model model) {
		if (adminLogFlag == 1) {
			MappingDetails md = mds.getById(studentId);
			model.addAttribute("map", md);
			return "editMapping";
		} else
			return "redirect:/error";
	}

	@PostMapping("/addMapping")
	public String addNewMapping(@ModelAttribute MappingDetails md, RedirectAttributes redirectAttributes) {
		if (adminLogFlag == 1) {
			mds.storeToDB(md);
			redirectAttributes.addFlashAttribute("successMessage", "Mapping Updated Successfully. ✔");
			return "redirect:/admin";
		} else
			return "redirect:/error";
	}

//	END OF ADMIN PANEL FUNCTION ROUTES !!

}
