package snippet;

public class Snippet {
	@RequestMapping(path="/regist/input", method = RequestMethod.GET)
		public String registInput(@ModelAttribute EmployeeForm employeeForm) {
			return "regist/regist_input";
		}
		
		@RequestMapping(path="/regist/check", method = RequestMethod.POST)
		public String registCheck(@ModelAttribute EmployeeForm employeeForm, Model model) {
	//		model.addAttribute("empPass", employeeForm.getEmpPass());
			
			
			return "regist/regist_check";
		}
		
		@RequestMapping(path="/regist/complete", method = RequestMethod.POST)
		public String registComplete(@ModelAttribute EmployeeForm employeeForm) {
			return "regist/regist_complete";
	
	    }
}

