import { Component, OnInit } from "@angular/core"
import { FormGroup, FormControl, Validators, FormBuilder } from "@angular/forms"

@Component({
  selector: "login-form",
  templateUrl: "./login-form.component.html"
})

export class LoginFormComponent {
  form: FormGroup;

  constructor(fb: FormBuilder) {
    this.form = fb.group({
      "login": ["", Validators.required],
      "password": ["", Validators.required]
    });
  }

  onLogin() {
    console.log(this.form);
  }

}

