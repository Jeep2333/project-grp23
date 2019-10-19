public void loginOnClick(String email, String Password){
	mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>()){
		public void onComplete(@NonNull Task<AuthResult> task){
			if(task.isSuccessful()){
				finish();
				startActivity(new Intent(getApplicationContent().ProfileActivity.class));

			}else{
				new CustomToast().Show_Toast(LoginActivity.this,view,"Could not login");
				
			}
		}
	}

}