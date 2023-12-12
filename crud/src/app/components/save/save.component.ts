import { Component } from '@angular/core';
import { ContactService } from 'src/app/services/contact.service';
import { FormBuilder, FormGroup } from '@angular/forms'
import { Router } from '@angular/router';

@Component({
  selector: 'app-save',
  templateUrl: './save.component.html',
  styleUrls: ['./save.component.css']
})
export class SaveComponent {

  form:FormGroup

  constructor(private contactService:ContactService, private formBuilder: FormBuilder, private router:Router){
    this.form = this.formBuilder.group({
      nombre : [''],
      apellido : [''],
      telefono : ['']   
    })
  }
  getform(){
    //console.log(this.form.value)
    this.contactService.addContact(this.form.value).subscribe({
      next : res => {
        this.router.navigate(['/contact-list'])
      },
      error : error => {
        console.log(error)
      }
    })
  }
}
