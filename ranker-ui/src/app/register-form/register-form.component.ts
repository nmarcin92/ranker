import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';

@Component({
  selector: 'app-register-form',
  templateUrl: './register-form.component.html',
  styleUrls: ['./register-form.component.css']
})
export class RegisterFormComponent {
  form: FormGroup;

  constructor(fb: FormBuilder) {
    this.form = fb.group({
      "login": ["", Validators.required],
      "username": ["", Validators.required],
      "password": ["", Validators.required]
    });
  }

  onLogin() {
    console.log(this.form);
  }

}

