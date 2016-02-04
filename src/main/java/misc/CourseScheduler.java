package misc;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CourseScheduler {

	public static String[] scheduleCourses(String[] coursesStr) {
		Set<Course> courses = new TreeSet<Course>();
		for (String courseStr : coursesStr) {
			try {
				Course course = new Course(courseStr);
				courses.add(course);
			} catch (Exception e) {
				e.printStackTrace();
				// In case of invalid input
				return new String[] {};
			}
		}
		List<String> orderedSchedule = new ArrayList<String>();
		boolean hasResolvedAll = true;
		while (courses.size() > 0) {
			Course courseToRemove = courses.iterator().next();
			if (courseToRemove.prerequisiteCourses.size() != 0) {
				hasResolvedAll = false;
				break;
			}
			orderedSchedule.add(courseToRemove.toString());
			courses.remove(courseToRemove);
			Iterator<Course> courseIt = courses.iterator();
			Set<Course> rebuildCourses = new HashSet<Course>();
			String courseNameToRemove = courseToRemove.toString();
			while (courseIt.hasNext()) {
				Course course = courseIt.next();
				if (course.prerequisiteCourses.contains(courseToRemove.toString())) {
					course.prerequisiteCourses.remove(courseNameToRemove);
					courseIt.remove();
					rebuildCourses.add(course);
				}
			}
			courses.addAll(rebuildCourses);
		}
		if (!hasResolvedAll) {
			// In case of deadlock courses
			return new String[] {};
		}
		int size = orderedSchedule.size();
		String out[] = new String[size];
		orderedSchedule.toArray(out);
		return out;
	}

	public static void main(String[] args) {
		System.out
				.println(Arrays.toString(CourseScheduler.scheduleCourses(new String[] { "CSE258: CSE244 CSE243 INTR100",
						"CSE221: CSE254 INTR100", "CSE254: CSE111 MATH210 INTR100", "CSE244: CSE243 MATH210 INTR100",
						"MATH210: INTR100", "CSE101: INTR100", "CSE111: INTR100", "ECE201: CSE111 INTR100", "ECE111: INTR100",
						"CSE243: CSE254", "INTR100:" })));
	}
}

class Course implements Comparable<Course> {

	String deptName;

	int courseNo;
	private static final Pattern VALID_COURSE_PATTERN = Pattern.compile("([A-Z]{3,4})([0-9]{3}):(( [A-Z]{3,4}[0-9]{3})*)");

	Set<String> prerequisiteCourses;

	public Course(String courseName) throws Exception {
		Matcher matcher = VALID_COURSE_PATTERN.matcher(courseName);
		if (!matcher.matches()) {
			throw new Exception("Format of course name is wrong");
		}
		this.deptName = matcher.group(1);
		String courseNoStr = matcher.group(2);
		courseNo = Integer.parseInt(courseNoStr);
		if (courseNo < 100)
			// In the worst case that the three digits contain leading zeroes
			throw new Exception("Course number should be between 100 and 999");
		String prerequisiteCourseStr = matcher.group(3).trim();
		if (prerequisiteCourseStr.length() > 0) {
			// This is to allow removal as and when prerequisites are resolved
			// (as asList returns unmodifiableList)
			prerequisiteCourses = new HashSet<String>(Arrays.asList(prerequisiteCourseStr.split(" ")));
		} else {
			prerequisiteCourses = new HashSet<String>();
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + courseNo;
		result = prime * result + ((deptName == null) ? 0 : deptName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		if (courseNo != other.courseNo)
			return false;
		if (deptName == null) {
			if (other.deptName != null)
				return false;
		} else if (!deptName.equals(other.deptName))
			return false;
		return true;
	}

	@Override
	public int compareTo(Course o) {
		int diff = prerequisiteCourses.size() - o.prerequisiteCourses.size();
		if (diff != 0)
			return diff;
		diff = courseNo - o.courseNo;
		if (diff != 0)
			return diff;
		else
			return deptName.compareTo(o.deptName);
	}

	@Override
	public String toString() {
		return deptName + courseNo;
	}
}
