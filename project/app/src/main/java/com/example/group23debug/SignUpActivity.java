public class SignUpActivity extends AppCompatActivity{
	
	private EditText firstNameEditText;
	private EditText lastNameEditText;
	private EditText studentEditText;
	private EditText emailEditText;
	private EditText passwordEditText;
	private EditText confirmPassEditText;

	FirebaseAuth mAuth;
	FirebaseDatabase mDatabase;

	@Override
	protected void onCreat(Bundle savedInstanceState){
		super.onCreat(savedInstanceState);
		setContentView(R.layout.activity_sign_up);

		myAuth = FirebaseAuth.getInstance();
		mDatabase = FirebaseDatabase.getInstance();

		setUpVariables();
	}


	public void signUpOnClick(View v){
		String firstName = firstNameEditText.getText().toString();
		String lastName = lastNameEditText.getText().toString();
		String studentNo = studentEditText.getText().toString();
		String email = emailEditText.getText().toString();
		String password = passwordEditText.getText().toString();

		final Student mStudent = new Student(firstName, lastName, studentNo, email, password);

		mAuth.creatUserWithEmailAndPassword(email, password).addOnCompleteListener(this,task);
		{
			if(task.isSuccessful()){
				mDatabase.getReference("Student").chil(mAuth.getCurrentUser().getUid()).setValue(mStudent).addOnCompleteListener(task) 
					if(task.isSuccessful()){
						finish();
						startActivity{
							new Intent(getApplicationContext(), ProfileActivity.class);

						}else{
							new CustomToast().Show_Toast(SignUpActivity.this, view, "FIresbase Database Error");
						}

				}
			}else{
				new CustomToast().Show_Toast(SignUpActivity.this, view, "Firebase Authentication Error");
			}
		}
	}
}