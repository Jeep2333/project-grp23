public class ProfileActivity extends AppCompatActivity{
	
	private FirebaseAuth mAuth;
	private FirebaseUser mUser;
	private DatabaseReference mDatabase;

	private TextView firstNameTextView;
	private TextView lastNameTextView;
	private TextView studentNameTextView;
	private TextView emailTexView;

	@Override

	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);

		mAuth = FirebaseAuth.getInstance();
		mUser = mAuth.getCurrentUser();
		mDatabase = FirebaseDatabase.getInstance().getReference();

		firstNameTextView = (TextView) findViewById(R.id.firstTextView);
		lastNameTextView = (TextView) findViewById(R.id.lastTextView);
		studentNameTextView = (TextView) findViewById(R.id.stNoTextView);
		emailTexView = (TextView) findViewById(R.id.emailText);

		if (mUser == null){
			finish();
			startActivity(new Intent(this, MainActivity.class()));

		}
		else{
			mDatabase.child("Students").child(mUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener()){
				@Override
				public void onDataChange(DataSnapshot dataSnapshot){
					Student student = dataSnapshot.getValue(Student.class);

					String firstName = student.getFirstName();
					String lastName = student.getLastName();
					String studentNo = student.getStudentNo();
					String email = student.getEmail();

					firstTextView.setText(firstName);
					lastNameTextView.setText(lastName);
					studentNameTextView.setText(studentNo);
					emailTexView.setText(email);

				}
				@Override
				public void onCancelled(DatabaseError databaseError){
					
				}
			}
		}
	}
}