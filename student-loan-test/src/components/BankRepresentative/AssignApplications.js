import React, { useState, useEffect } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import axios from 'axios';
import './AssignApplications.css';

const axiosInstance = axios.create({
  baseURL: 'http://localhost:8080/StudentLoanServices',
});

const AssignApplications = () => {
  const navigate = useNavigate();
  const [userName, setUserName] = useState('John Doe'); 
  const [representativeId, setRepresentativeId] = useState(null); 
  const [representatives, setRepresentatives] = useState([]); 
  const [applications, setApplications] = useState([]); 
  const [selectedAssignees, setSelectedAssignees] = useState({}); 
  const [message, setMessage] = useState({ text: '', type: '' }); 
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const isLoggedIn = localStorage.getItem('isLoggedIn'); 
    if (isLoggedIn !== 'true') {
      navigate('/login', { replace: true });
    }
  }, [navigate]);

  // Handle logout
  const handleLogout = () => {
    localStorage.removeItem('isLoggedIn');
    navigate('/login', { replace: true });
  };
  
  const fetchRepresentatives = async () => {
    try {
      const response = await axiosInstance.get('/api/bankRepresentative/all');
      const reps = response.data || [];
      const mappedReps = reps.map(rep => ({
        id: rep.employeeId,
        // name: `${rep.user.userName}`,
        name: rep.userName,
      }));
      console.log("MappedRep:", mappedReps)
      setRepresentatives(mappedReps);
    } catch (err) {
    }
  };

  // Fetch applications (Draft and unassigned)
  const fetchApplications = async () => {
    try {
      const response = await axiosInstance.get('/api/application/getDraftApplications');
      if (response.data.success) {
        const apps = response.data.data || [];
        const mappedApps = apps.map(app => ({
          id: app.applicationId,
          studentName: app.studentName,
          amount: app.amountRequested,
          program: app.program,
          submittedDate: app.submittedDate,
          assigneeId: app.assigneeId,
          status: app.status,
        }));
        const draftApps = mappedApps.filter(app => app.status === 'DRAFT');
        setApplications(draftApps);
        const initialAssignees = {};
        draftApps.forEach(app => {
          initialAssignees[app.id] = ''; 
        });
        setSelectedAssignees(initialAssignees);
      } else {
        setMessage({ text: response.data.message || 'Failed to fetch applications.', type: 'error' });
      }
    } catch (err) {
      setMessage({ text: err.response?.data?.message || 'Failed to fetch applications.', type: 'error' });
    }
  };

  // Handle assignee selection change
  const handleAssigneeChange = (applicationId, selectedRepId) => {
    setSelectedAssignees(prev => ({
      ...prev,
      [applicationId]: selectedRepId,
    }));
  };

  // Handle assigning an application to the selected representative
  const handleAssign = async (applicationId) => {
    const assigneeId = selectedAssignees[applicationId];
    if (!assigneeId) {
      setMessage({ text: 'Please select a representative to assign the application.', type: 'error' });
      return;
    }
    try {
      const response = await axiosInstance.post(
        `/api/application/assign/${applicationId}?assigneeId=${parseInt(assigneeId)}`,
        {},
        { headers: { 'Content-Type': 'application/json' } }
      );
      if (response.data.success) {
        setMessage({ text: response.data.message, type: 'success' });
        fetchApplications();
      } else {
        setMessage({ text: response.data.message || 'Failed to assign application.', type: 'error' });
      }
    } catch (err) {
      setMessage({ text: err.response?.data?.message || 'Failed to assign application.', type: 'error' });
    }
    setTimeout(() => {
      setMessage({ text: '', type: '' });
    }, 3000);
  };

  // Load initial data
  useEffect(() => {
    const fetchData = async () => {
      setLoading(true);
      try {
        await Promise.all([fetchRepresentatives(), fetchApplications()]);
      } catch (err) {
        setMessage({ text: 'An error occurred while fetching data.', type: 'error' });
      } finally {
        setLoading(false);
      }
    };
    fetchData();
  }, [navigate]);

  return (
    <div className="assign-applications-container">
      <header className="assign-applications-header">
        <div className="assign-applications-branding">
          <Link to="/bank-representative/dashboard" className="assign-applications-logo">Student Loan Service</Link>
        </div>
        <div className="assign-applications-actions">
          <Link to="/bank-representative/dashboard" className="assign-applications-nav-link">Dashboard</Link>
          <Link to="/bank-representative/process-applications" className="assign-applications-nav-link">Process Applications</Link>
          <button className="assign-applications-btn-logout" onClick={handleLogout}>
            <i className="fas fa-sign-out-alt"></i> Logout
          </button>
        </div>
      </header>

      <main className="assign-applications-main">
        {loading ? (
          <div className="assign-applications-loading">
            Loading...
          </div>
        ) : (
          <>
            <h1 className="assign-applications-title">Assign Loan Applications</h1>
            <p className="assign-applications-subtitle">Assign pending draft applications for processing.</p>

            {message.text && (
              <div className={message.type === 'success' ? 'assign-applications-success-message' : 'assign-applications-alert'}>
                <span>{message.text}</span>
                {message.type === 'error' && (
                  <button className="assign-applications-close-btn" onClick={() => setMessage({ text: '', type: '' })}>×</button>
                )}
              </div>
            )}

            <div className="assign-applications-content">
              <div className="assign-applications-pending-list">
                <h2 className="assign-applications-section-title">Unassigned Draft Applications</h2>
                {applications.length === 0 ? (
                  <p className="assign-applications-no-applications">No unassigned draft applications available.</p>
                ) : (
                  <table className="assign-applications-table">
                    <thead>
                      <tr>
                        <th>Student Name</th>
                        <th>Loan Amount</th>
                        <th>Program</th>
                        <th>Submitted Date</th>
                        <th>Assign To</th>
                        <th>Action</th>
                      </tr>
                    </thead>
                    <tbody>
                      {applications.map(app => (
                        <tr key={app.id}>
                          <td>{app.studentName}</td>
                          <td>${app.amount}</td>
                          <td>{app.program}</td>
                          <td>{app.submittedDate}</td>
                          <td>
                            <select
                              value={selectedAssignees[app.id] || ''}
                              onChange={(e) => handleAssigneeChange(app.id, e.target.value)}
                              className="assign-applications-assignee-select"
                            >
                              <option value="" disabled>Select Representative</option>
                              {representatives.map(rep => (
                                <option key={rep.id} value={rep.id}>
                                  {rep.name}
                                </option>
                              ))}
                            </select>
                          </td>
                          <td>
                            <button
                              className="assign-applications-action-btn assign-applications-assign-btn"
                              onClick={() => handleAssign(app.id)}
                            >
                              Assign
                            </button>
                          </td>
                        </tr>
                      ))}
                    </tbody>
                  </table>
                )}
              </div>
            </div>
          </>
        )}
      </main>
    </div>
  );
};

export default AssignApplications;